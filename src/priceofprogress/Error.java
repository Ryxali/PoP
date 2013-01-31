package priceofprogress;

import javax.swing.JOptionPane;

public class Error implements Runnable{
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
	
	private static Error err;
	private Error(){
		
	}
	
	public static Error get(){
		if(err == null){
			err = new Error();
			err.run();
		}
		return err;
	}

	@Override
	public void run() {
		
	}
}
