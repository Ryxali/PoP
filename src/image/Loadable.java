package image;
/**
 * This interface acts as a link between all
 * image based objects, allowing for simpler
 * resource loading/unloading.
 * @author Niklas L
 * @see image.ImageStore
 * @see image.AnimatedImage
 * @see image.AnimationStore
 *
 */
public interface Loadable {
	/**
	 * reloads all image based resources of this object.
	 * @see image.Loadable
	 */
	public void reload();
	/**
	 * unloads all image based resources of this object.
	 * @see image.Loadable
	 */
	public void unload();
}
