package practice.demo;

import java.io.File;

/**
 * @author Jay Yang
 * @date 2022/9/4
 */
public class BatchRenameDemo {

    public static void main(String[] args) {

        File dir = new File("C:\\Users\\jay\\Documents\\WeChat Files\\wxid_ijci25jo08lp21\\FileStorage\\File\\2022-09\\D2207240008_成都_绵阳杨杰蔡瑶_看样\\修改1\\修改");
        int i= 1;
        for (File file : dir.listFiles()) {
            String suf = file.getName().substring(file.getName().indexOf("."));
            file.renameTo(new File(file.getParent(), "需要调整" + i++ + suf));
        }

    }

}
