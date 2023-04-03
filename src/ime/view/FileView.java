package ime.view;

public class FileView extends TextView {

  public FileView(Appendable out, Readable in) {
    super(out, in);
  }

  @Override
  public void textPrompt() {
    //no contents
  }
}
