package brad.util.sys;

public interface PromptUtilities {
    void inform(String title, String message);
    void warn(String title, String message);
    boolean ask(String title, String question);
    void exception(String title, Exception ex);
}
