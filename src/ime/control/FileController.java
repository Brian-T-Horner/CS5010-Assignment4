package ime.control;

/**
 * Controller for asynchronously running a provided file.
 */
public class FileController extends AbstractController {
  /**
   * Contructor for the creation of a IME.control.Controller object.
   *
   * @param in  Input stream for the controller.
   * @param out Output stream for the controller.
   */
  public FileController(Readable in, Appendable out) {
    super(in, out);
  }

  @Override
  protected void insertCursor() {
    // Empty for implementation.
  }
}
