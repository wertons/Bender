import org.junit.Test;

import static org.junit.Assert.*;


public class BenderTest2 {


    @Test
    public void optimalWalkTest() {
        String mapa;
        Bender bender;

        mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "#     #\n" +
                "#     #\n" +
                "#$    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        bender = new Bender(mapa);
        assertEquals(4, bender.bestRun());

        mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "##### #\n" +
                "#     #\n" +
                "#$    #\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        bender = new Bender(mapa);
        assertEquals(10, bender.bestRun());


        mapa = "" +
                "#######\n" +
                "# X   #\n" +
                "##### #\n" +
                "#     #\n" +
                "#     #\n" +
                "# #####\n" +
                "#     #\n" +
                "#   $ #\n" +
                "#     #\n" +
                "#     #\n" +
                "#######";
        bender = new Bender(mapa);
        assertEquals(16, bender.bestRun());


        mapa = "" +
                "##############\n" +
                "# X          #\n" +
                "#      #     #\n" +
                "## ##  #     #\n" +
                "#      #     #\n" +
                "#      #     #\n" +
                "#            #\n" +
                "#      #### ##\n" +
                "##### ##    ##\n" +
                "#   # #      #\n" +
                "# $ ###      #\n" +
                "#   #        #\n" +
                "#   #        #\n" +
                "#   #        #\n" +
                "#   #        #\n" +
                "#   #        #\n" +
                "#            #\n" +
                "#   #        #\n" +
                "##############";
        bender = new Bender(mapa);
        assertEquals(39, bender.bestRun());


        mapa = "" +
                "##############\n" +
                "# X           #\n" +
                "#      ###    #\n" +
                "#    ### ###  #\n" +
                "#  ###     #  #\n" +
                "#  #    $  #  #\n" +
                "#  #####   #  #\n" +
                "#      #   #  #\n" +
                "#      #   #  #\n" +
                "#             #\n" +
                "###############";
        bender = new Bender(mapa);
        assertEquals(18, bender.bestRun());


        mapa = "" +
                "##############\n" +
                "# X           #\n" +
                "#             #\n" +
                "#  #########  #\n" +
                "#             #\n" +
                "# ########### #\n" +
                "#          #  #\n" +
                "#          ####\n" +
                "#             #\n" +
                "#             #\n" +
                "## ###  ###   ##\n" +
                "#    #  #     #\n" +
                "#    ## #     #\n" +
                "#     # #     #\n" +
                "#     # #     #\n" +
                "#     ###     #\n" +
                "#######       #\n" +
                "#             #\n" +
                "#    ### ###  #\n" +
                "#  ###     #  #\n" +
                "#  #       #  #\n" +
                "#  #####   #  #\n" +
                "#  #$  #   ####\n" +
                "#  #   #      #\n" +
                "#  #   ###### #\n" +
                "#  #       #  #\n" +
                "#          #  #\n" +
                "###############";
        bender = new Bender(mapa);
        assertEquals(51, bender.bestRun());

        // Amb Teleport
        mapa = "" +
                "#######\n" +
                "#     #\n" +
                "#     #\n" +
                "#    T#\n" +
                "#     #\n" +
                "# X   #\n" +
                "##### #\n" +
                "#     #\n" +
                "#     #\n" +
                "# #####\n" +
                "#     #\n" +
                "#   $ #\n" +
                "#     #\n" +
                "#   T #\n" +
                "#######";
        bender = new Bender(mapa);
        assertEquals(7, bender.bestRun());


        mapa = "" +
                "##############\n" +
                "# X           #\n" +
                "#             #\n" +
                "#  #########  #\n" +
                "#             #\n" +
                "# ########### #\n" +
                "#          #T #\n" +
                "#          ####\n" +
                "#             #\n" +
                "#             #\n" +
                "## ###  ###   ##\n" +
                "#    #  #     #\n" +
                "#    ## #     #\n" +
                "#     # #     #\n" +
                "#     # #     #\n" +
                "#     ###     #\n" +
                "#######       #\n" +
                "#             #\n" +
                "#    ### ###  #\n" +
                "#  ###     #  #\n" +
                "#  #T      #  #\n" +
                "#  #####   #  #\n" +
                "#  #$  #   ####\n" +
                "#  #   #      #\n" +
                "#  #   ###### #\n" +
                "#  #       #  #\n" +
                "#          #  #\n" +
                "###############";
        bender = new Bender(mapa);
        assertEquals(45, bender.bestRun());
    }
}