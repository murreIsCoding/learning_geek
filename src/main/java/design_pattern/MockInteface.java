package design_pattern;


/**
 * 如何决定该用抽象类还是接口？刚刚的讲解可能有些偏理论，
 * 现在，我们就从真实项目开发的角度来看一下，在代码设计、编程开发的时候，什么时候该用抽象类？什么时候该用接口？
 * 实际上，判断的标准很简单。如果我们要表示一种 is-a 的关系，并且是为了解决代码复用的问题，我们就用抽象类；
 * 如果我们要表示一种 has-a 关系，并且是为了解决抽象而非代码复用的问题，那我们就可以使用接口。
 */
public class MockInteface {
    protected MockInteface() {}
    public void funcA() throws MethodUnSupportedException {
        throw new MethodUnSupportedException();
    }
}