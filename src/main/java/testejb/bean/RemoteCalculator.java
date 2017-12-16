package testejb.bean;

import javax.ejb.Remote;


@Remote(RemoteCalculator.class)
public interface RemoteCalculator {

    int add(int a, int b);

    int subtract(int a, int b);
}
