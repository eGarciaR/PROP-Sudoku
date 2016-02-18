

import controllers.presentation.PresentationCtrl;

public class Main {
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            PresentationCtrl pc = PresentationCtrl.getInstance();           
            @Override
            public void run() {
                pc.initPresentation();
            } 
        });
    }
}
