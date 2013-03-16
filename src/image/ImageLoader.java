package image;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/**
 * This class appears to be utterly useless since we can't load images outside
 * the 'init' sequence of a state or during the game loop. This will stick
 * around though in-case we find a workaround.
 * 
 * @author Niklas L
 * 
 * @deprecated images can't be loaded outside of the <b>init()</b> or <b>
 * render()</b> part of a state and therefore this class is useless.
 * 
 */
public class ImageLoader implements Runnable {

	private static ImageLoader imgLoader;
	private static Thread thread;
	private int stateToLoad;
	private ArrayList<? super Object> refData = new ArrayList<Object>();

	public static ImageLoader get() {
		if (imgLoader == null || thread == null) {
			imgLoader = new ImageLoader();
			thread = new Thread(imgLoader);
			thread.start();
		}
		return imgLoader;
	}

	public void queue(Object ref1, Object... refs) {
		refData.add(ref1);
		for (int i = 0; i < refs.length; i++) {
			refData.add(refs[i]);
		}
	}

	public void queue(int state) {
		stateToLoad = state;
		thread.start();
	}
	public boolean isLoading(){
		return thread.isInterrupted();
	}

	public void stop() {
		thread.interrupt();
	}

	public void resume() {
		
	}

	private void loadImages() {
		/*
		 * ArrayList<ImageStore> imgs = new ArrayList<ImageStore>();
		 * ArrayList<AnimationStore> animImgs = new ArrayList<AnimationStore>();
		 * ArrayList<ImageCluster> clusterImgs = new ArrayList<ImageCluster>();
		 * for (int i = 0; i < refData.size(); i++) { if(refData.get(i)
		 * instanceof ImageStore){ imgs.add((ImageStore) refData.get(i)); }else
		 * if(refData.get(i) instanceof AnimationStore){
		 * animImgs.add((AnimationStore) refData.get(i)); }else
		 * if(refData.get(i) instanceof ImageCluster){
		 * clusterImgs.add((ImageCluster) refData.get(i)); }else{
		 * System.out.println("WARNING! " + refData.get(i).getClass() +
		 * " not applicable for image loading. Only objects of the type " +
		 * "ImageStore, AnimationStore, ImageCluster or its extensions are permitted!"
		 * ); } refData.remove(i); } for (int i = 0; i < imgs.size(); i++) {
		 * imgs.get(i).reload(); } for (int i = 0; i < animImgs.size(); i++) {
		 * System.out.println(animImgs.get(i).getClass() + " loaded");
		 * animImgs.get(i).reload(); } for (int i = 0; i < clusterImgs.size();
		 * i++) { clusterImgs.get(i).reload(); }
		 */
		try {
			state.State.getState(stateToLoad).getState().init(null, null);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		loadImages();
		thread.interrupt();
	}

}
