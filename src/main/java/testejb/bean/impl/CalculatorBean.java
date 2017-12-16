package testejb.bean.impl;


import testejb.bean.RemoteCalculator;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(RemoteCalculator.class)
public class CalculatorBean implements RemoteCalculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}
