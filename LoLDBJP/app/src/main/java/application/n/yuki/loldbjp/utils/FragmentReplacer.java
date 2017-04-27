package application.n.yuki.loldbjp.utils;


import application.n.yuki.loldbjp.view.base.BaseFragment;

public interface FragmentReplacer {
    void replaceFragment(BaseFragment fragment);
    void replaceFragment(BaseFragment fragment, boolean animated);
}
