package model;


public class ToDo {
   private boolean isDone;
   private String text;

    ToDo(String text, boolean isDone){ //Constuktor, a todoItem is not done when created
        this.text=text;
        this.isDone=false;
    }

    private boolean getIsDone(boolean isDone){
        return isDone;
}
    private void setActive() {
        this.isDone = false;
    }

    private void setInaktive(){
        this.isDone=true;
    }
    private String getText(String text){
        return text;
    }
   private void setString(){
        this.text=text;
   }

}
