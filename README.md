**darcy**
=========
[![Build Status](https://drone.io/github.com/darcy-framework/darcy/status.png)](https://drone.io/github.com/darcy-framework/darcy/latest)

A framework for writing [**page objects**][6] in order to automate interaction with graphical user interfaces. Page objects are classes that model what a user can see and do with a specific page. In darcy each page, or subset of a page, is called a [View](https://github.com/darcy-framework/darcy/blob/master/src/main/java/com/redhat/darcy/ui/View.java).

**darcy** is:

* Automation library agnostic -- any library that can find UI elements and interact with them can work with darcy. [Selenium WebDriver](https://code.google.com/p/selenium/) support is provided by [darcy-webdriver][3].
* Flexible and extendable by virtue of a declarative, **element-based DSL**. Write your page objects in terms of the UI buttons, labels, and widgets that you see. Wrap complex behavior in reusable, custom element types.
* Dependent on Java 8. [Get your lambda on](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)!

example page object
===================
```java
import static com.redhat.darcy.ui.elements.Elements.textInput;
import static com.redhat.darcy.ui.elements.Elements.button;
import static com.redhat.synq.Synq.after;

@RequireAll
public class MyHomePage extends AbstractView {
  private TextInput login = textInput(By.id("login"));
  private TextInput password = textInput(By.id("password"));
  private Button submit = button(By.id("submit"));

  @NotRequired
  private Label errorMsg = label(By.class("error"));

  public AccountDetails login(Credentials credentials) {
    login.clearAndType(credentials.login());
    password.clearAndType(credentials.password());
    
    return after(submit::click)
        .expect(transition().to(new AccountDetails())
        .failIf(errorMsg::isDisplayed)
          .throwing(new InvalidLoginException(credentials, errorMsg.readText()))
        .waitUpTo(1, MINUTES);
  }
}
```

getting started
===============

Check out the [getting started][5] tutorials to learn more about darcy.


contributing
============

Pull requests welcome and encouraged! Please read the documentation to get started.

I have homework towards encouraging contribution:
- [ ] Create issues for missing features
- [ ] Document architecture
- [x] Establish unit testing framework (if necessary) (it wasn't necessary)
- [ ] Add more unit tests (in progress)

license
=======

**darcy** is licensed under [version 3 of the GPL][2].


  [1]: https://github.com/darcy-framework/synq
  [2]: https://www.gnu.org/copyleft/gpl.html
  [3]: https://github.com/darcy-framework/darcy-webdriver
  [4]: https://github.com/darcy-framework/darcy-web
  [5]: https://github.com/darcy-framework/darcy/wiki/Getting-Started-%231:-Project-setup-and-darcy-fundamentals
  [6]: http://martinfowler.com/bliki/PageObject.html
