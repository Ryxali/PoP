package priceofprogress;

import javax.swing.JOptionPane;

public class PoPError implements Runnable{
	public void exitWithPrompt(String message){
		JOptionPane.showConfirmDialog(null, message);
		System.exit(0);
	}
	
	public void safeExitWithPrompt(String message){
		JOptionPane.showConfirmDialog(null, message);
		safeExit();
	}
	
	public void safeExit(){
		System.exit(0);
	}
	
	private static PoPError err;
	private PoPError(){
		
	}
	
	public static PoPError get(){
		if(err == null){
			err = new PoPError();
			err.run();
		}
		return err;
	}

	@Override
	public void run() {
	}
}
