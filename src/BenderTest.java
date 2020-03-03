import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class BenderTest {
    @Test
    public void test01() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# $   #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSS", bender.run());
    }

    @Test
    public void test02() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#   $ #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSEE", bender.run());
    }

    @Test
    public void test03() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#    $#\n" +
                "#     #\n" +
                "# X   #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEENNN", bender.run());
    }

    @Test
    public void test04() {
        String mapa = "" +
                "#######\n" +
                "#    $#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNN", bender.run());
    }


    @Test
    public void test05() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X###\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWW", bender.run());
    }

    @Test
    public void test06() {
        String mapa = "" +
                "###########\n" +
                "#T        #\n" +
                "#  ########\n" +
                "#$        #\n" +
                "#   X     #\n" +
                "# #####  T#\n" +
                "#       ###\n" +
                "#         #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("EEEEESSS", bender.run());
    }

    @Test
    public void test07() {
        String mapa = "" +
                "###########\n" +
                "#T       $#\n" +
                "#  ########\n" +
                "#         #\n" +
                "#   X     #\n" +
                "#         #\n" +
                "#       ###\n" +
                "#     T   #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SSSEEEEEEEEEE", bender.run());
    }


    @Test
    public void test08() {
        String mapa = "" +
                "###########\n" +
                "######    #\n" +
                "#$   #    #\n" +
                "#### #    #\n" +
                "#  # #    #\n" +
                "#  #X#    #\n" +
                "#  #I#    #\n" +
                "#  ###    #\n" +
                "#         #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SNNNNWWW", bender.run());
    }

    @Test
    public void test09() {
        String mapa = "" +
                "#######\n" +
                "#T    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  $ T#\n" +
                "#  X###\n" +
                "#I    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEEWWWWNNNNNNNNWWWWWW", bender.run());
    }

    @Test
    public void test10() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  X  #\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWWWSSSSS", bender.run());
    }

    @Test
    public void test11() {
        String mapa = "" +
                "#######\n" +
                "# #  I#\n" +
                "#     #\n" +
                "#  T  #\n" +
                "#  #  #\n" +
                "#T X  #\n" +
                "#$    #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWSSS", bender.run());
    }

    @Test
    public void test12() {
        String mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#  $  #\n" +
                "#  X  #\n" +
                "#  I  #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SNN", bender.run());
    }

    @Test
    public void test13() {
        String mapa = "" +
                "#######\n" +
                "#    I#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#$ X  #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SEENNNNNWWWWSSSS", bender.run());
    }

    @Test
    public void test14() {
        // Irregular
        String mapa = "" +
                "   #######\n" +
                "   # XTI #\n" +
                "   #    $#\n" +
                "####    #####\n" +
                "#          T#\n" +
                "####     ####\n" +
                "   #    I#\n" +
                "   #     #\n" +
                "   #######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSSEEENNNWWWWWWWEEEEEEEEEEEES", bender.run());
    }

    @Test
    public void test15() {
        // Impossible
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#$    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertNull(bender.run());
    }

    @Test
    public void test16() {
        String mapa = "" +
                "#######\n" +
                "#$    #\n" +
                "# X   #\n" +
                "# T   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#   T #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#T   T#\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSSSENNNNNNNNNNN", bender.run());
    }

    @Test
    public void test17() {
        String mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "# T $ #\n" +
                "#   T #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#    T#\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSSSSSSENNN", bender.run());
    }

    @Test
    public void test18() {
        String mapa = "" +
                "###########\n" +
                "###### I  T#\n" +
                "#T   #    #\n" +
                "#### #    #\n" +
                "#  # #    #\n" +
                "#  # #    #\n" +
                "#  # #    #\n" +
                "#  # #    #\n" +
                "#  # #    #\n" +
                "#  #X#    #\n" +
                "#  #I#    #\n" +
                "# T###T   #\n" +
                "# $       #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SNNNNNNNNWWWWWWWSSSSSSSSSSS", bender.run());
    }

    @Test
    public void test19() {
        String mapa = "" +
                "#######\n" +
                "#$X   #\n" +
                "# T I #\n" +
                "#   T #\n" +
                "#     #\n" +
                "#     #\n" +
                "#    T#\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "#     #\n" +
                "# T T #\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSSSSSSSSSEEENNNNNNNNNNNWWW", bender.run());
    }

    @Test
    public void test20() {
        // Passa dues vegades pel mateix lloc
        String mapa = "" +
                "########\n" +
                "# ###  #\n" +
                "# #X#  #\n" +
                "#      #\n" +
                "#      #\n" +
                "#      #\n" +
                "#      #\n" +
                "#      #\n" +
                "#      #\n" +
                "#  I $ #\n" +
                "# ###  #\n" +
                "#      #\n" +
                "#      #\n" +
                "#      #\n" +
                "#      #\n" +
                "#      #\n" +
                "########";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSSSNNNNNNNSSSSSSSEE", bender.run());
    }

    @Test
    public void test21() {
        String mapa = "" +
                "###########\n" +
                "#    X    #\n" +
                "#         #\n" +
                "#  T   T  #\n" +
                "#    T $  #\n" +
                "#  T   T  #\n" +
                "#         #\n" +
                "#         #\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SSSS", bender.run());
    }

    @Test
    public void test22() {
        String mapa = "" +
                "###########\n" +
                "#    X    #\n" +
                "#         #\n" +
                "#  T      #\n" +
                "#    T    #\n" +
                "#  T   T  #\n" +
                "#         #\n" +
                "#        $#\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSEE", bender.run());
    }

    @Test
    public void test23() {
        String mapa = "" +
                "###########\n" +
                "#    X    #\n" +
                "#         #\n" +
                "#  T      #\n" +
                "#    T    #\n" +
                "#  T      #\n" +
                "#         #\n" +
                "#        $#\n" +
                "###########";
        Bender bender = new Bender(mapa);
        assertEquals("SSSSSEEEEEE", bender.run());
    }

    static List<StringBuilder> constructMap(int sizex, int sizey) {
        List<StringBuilder> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < sizex; i++) {
            s.append('#');
        }
        list.add(s);

        for (int i = 0; i < sizey; i++) {
            s = new StringBuilder();
            s.append('#');
            for (int j = 0; j < sizex - 2; j++) {
                s.append(' ');
            }
            s.append('#');
            list.add(s);
        }

        s = new StringBuilder();
        for (int i = 0; i < sizex; i++) {
            s.append('#');
        }
        list.add(s);
        return list;
    }

    static String convertString(List<StringBuilder> list) {
        String finalMap = "";
        for (int i = 0; i < list.size()-1; i++) {
            finalMap += list.get(i).toString() + "\n";
        }
        finalMap += list.get(list.size()-1);
        System.out.println(finalMap);
        return finalMap;
    }

    @Test
    public void test24() {
        final int SIZEX = 510;
        final int SIZEY = 5;

        List<StringBuilder> list = constructMap(SIZEX, SIZEY);
        list.get(1).setCharAt(6, 'X');
        list.get(5).setCharAt(SIZEX-2, '$');

        Bender bender = new Bender(convertString(list));
        assertEquals("SSSSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE",
                bender.run());
    }

    @Test
    public void test25() {
        final int SIZEX = 1000;
        final int SIZEY = 2000;

        List<StringBuilder> list = constructMap(SIZEX, SIZEY);
        list.get(1).setCharAt(6, 'X');
        list.get(1).setCharAt(SIZEX-2, 'I');
        list.get(1).setCharAt(1, '$');

        Bender bender = new Bender(convertString(list));
        assertEquals("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEENNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                bender.run());
    }

}