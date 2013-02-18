package image;

import java.util.ArrayList;
/**
 * This class appears to be utterly useless since we can't load images outside the '<init>' sequence of
 * a state or during the game loop. This will stick around though in-case we find a workaround.
 * @author Niklas L
 *
 */
public class ImageLoader implements Runnable {
	
	private static ImageLoader imgLoader;
	private static Thread thread;
	private ArrayList<?super Object> refData = new ArrayList<Object>();
	
	public static ImageLoader get(){
		if(imgLoader == null || thread == null){
			imgLoader = new ImageLoader();
			thread = new Thread(imgLoader);
			thread.start();
		}
		return imgLoader;
	}
	
	public void queue(Object ref1, Object... refs){
		refData.add(ref1);
		for (int i = 0; i < refs.length; i++) {
			refData.add(refs[i]);
		}
	}
	
	public void stop(){
		thread.interrupt();
	}
	
	public void resume(){
		thread.start();
	}
	
	private void loadImages(){
		ArrayList<ImageStore> imgs = new ArrayList<ImageStore>();
		ArrayList<AnimationStore> animImgs = new ArrayList<AnimationStore>();
		ArrayList<ImageCluster> clusterImgs = new ArrayList<ImageCluster>();
		for (int i = 0; i < refData.size(); i++) {
			if(refData.get(i) instanceof ImageStore){
				imgs.add((ImageStore) refData.get(i));
			}else if(refData.get(i) instanceof AnimationStore){
				animImgs.add((AnimationStore) refData.get(i));
			}else if(refData.get(i) instanceof ImageCluster){
				clusterImgs.add((ImageCluster) refData.get(i));
			}else{
				System.out.println("WARNING! " + refData.get(i).getClass() + 
						" not applicable for image loading. Only objects of the type " +
						"ImageStore, AnimationStore, ImageCluster or its extensions are permitted!");
			}
			refData.remove(i);
		}
		for (int i = 0; i < imgs.size(); i++) {
			imgs.get(i).reload();
		}
		for (int i = 0; i < animImgs.size(); i++) {
			System.out.println(animImgs.get(i).getClass() + " loaded");
			animImgs.get(i).reload();
		}
		for (int i = 0; i < clusterImgs.size(); i++) {
			clusterImgs.get(i).reload();
		}
		
	}
	
	@Override
	public void run() {
		while(!thread.isInterrupted()){
			loadImages();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
