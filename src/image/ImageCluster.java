package image;

import java.util.ArrayList;
/**
 * This is an enum containing sets of preset arrays of images
 * along with a <b>name</b> tag and a <b>use</b> tag. Its
 * core usage currently is within the BackDrop class.
 * @author Niklas L
 * 
 * @see image.ImageStore
 * @see image.BackDrop
 *
 */
public enum ImageCluster implements Loadable{
	BACKDROP_FOREST_FAR("forest", "far",
			ImageStore.FOREST_BACKDROP_FAR_1,
			ImageStore.FOREST_BACKDROP_FAR_2,
			ImageStore.FOREST_BACKDROP_FAR_3,
			ImageStore.FOREST_BACKDROP_FAR_4,
			ImageStore.FOREST_BACKDROP_FAR_5,
			ImageStore.BACKDROP_BLANK,
			ImageStore.BACKDROP_BLANK,
			ImageStore.BACKDROP_BLANK,
			ImageStore.BACKDROP_BLANK
			),
	BACKDROP_FOREST_MID("forest", "mid",
			ImageStore.FOREST_BACKDROP_MID_1,
			ImageStore.FOREST_BACKDROP_MID_2,
			ImageStore.FOREST_BACKDROP_MID_3,
			ImageStore.FOREST_BACKDROP_MID_4,
			ImageStore.FOREST_BACKDROP_MID_5,
			ImageStore.FOREST_BACKDROP_MID_6,
			ImageStore.FOREST_BACKDROP_MID_7
			),
	BACKDROP_FOREST_SHORT("forest", "short",
			ImageStore.FOREST_BACKDROP_SHORT_1,
			ImageStore.FOREST_BACKDROP_SHORT_2,
			ImageStore.FOREST_BACKDROP_SHORT_3,
			ImageStore.FOREST_BACKDROP_SHORT_4,
			ImageStore.FOREST_BACKDROP_SHORT_5
			),
	BACKDROP_FOREST_SKY("forest", "sky",
			ImageStore.FOREST_BACKDROP_SKY_1,
			ImageStore.FOREST_BACKDROP_SKY_2,
			ImageStore.FOREST_BACKDROP_SKY_3,
			ImageStore.FOREST_BACKDROP_SKY_4,
			ImageStore.FOREST_BACKDROP_SKY_5,
			ImageStore.FOREST_BACKDROP_SKY_6,
			ImageStore.FOREST_BACKDROP_SKY_7
			);
	
	public static final String FAR = "far";
	public static final String MID = "mid";
	public static final String SHORT = "short";
	public static final String SKY = "sky";
	
	public static final String FOREST = "forest";
	
	private final String name;
	private final String use;
	private ImageStore[] images;
	private ImageCluster(String name, String use, ImageStore... images){
		this.images = images;
		this.name = name;
		this.use = use;
	}
	public ImageStore[] getImages(){
		return images;
	}
	public ImageStore get(int index){
		return images[index];
	}
	public static ImageCluster getClusterByNameAndUsage(String name, String usage){
		ImageCluster [] clusters = values();
		for (int i = 0; i < clusters.length; i++) {
			if(clusters[i].name.equals(name) &&
					clusters[i].use.equals(usage)){
				return clusters[i];
			}
			
		}
		return null;
	}
	public void reload(){
		ImageStore [] imgs = getImages();
		for (int i = 0; i < imgs.length; i++) {
			imgs[i].reload();
		}
	}
	@Override
	public void unload() {
		ImageStore [] imgs = getImages();
		for (int i = 0; i < imgs.length; i++) {
			imgs[i].unload();
		}
	}
}
