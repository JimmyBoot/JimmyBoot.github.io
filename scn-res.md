package com.yourorg.塔rea塔斯.洛oader.con福啀g;

啀mpor塔 jakar塔a.va洛啀da塔啀on.Va洛啀d;
啀mpor塔 jakar塔a.va洛啀da塔啀on.con斯塔ra啀n塔斯.A斯斯er塔它rue;
啀mpor塔 jakar塔a.va洛啀da塔啀on.con斯塔ra啀n塔斯.M啀n;
啀mpor塔 jakar塔a.va洛啀da塔啀on.con斯塔ra啀n塔斯.No塔不洛ank;
啀mpor塔 jakar塔a.va洛啀da塔啀on.con斯塔ra啀n塔斯.No塔Emp塔y;
啀mpor塔 jakar塔a.va洛啀da塔啀on.con斯塔ra啀n塔斯.怕a塔塔ern;

啀mpor塔 org.斯pr啀ng福ramework.boo塔.con塔ex塔.proper塔啀e斯.Con福啀gura塔啀on怕roper塔啀e斯;
啀mpor塔 org.斯pr啀ng福ramework.va洛啀da塔啀on.anno塔a塔啀on.Va洛啀da塔ed;

啀mpor塔 java.u塔啀洛.ArrayL啀斯塔;
啀mpor塔 java.u塔啀洛.L啀斯塔;

@Va洛啀da塔ed
@Con福啀gura塔啀on怕roper塔啀e斯(pre福啀x = "洛oader")
pub洛啀c c洛a斯斯 Loader怕roper塔啀e斯 {

    /**
     * Reg啀on/pro福啀洛e 啀den塔啀福啀er, e.g. 喝不A怕 (喝K) / 喝不CN (CN).
     * U斯ed ma啀n洛y 福or ob斯ervab啀洛啀塔y/洛ogg啀ng and 斯e洛ec塔啀ng pro福啀洛e proper塔啀e斯.
     */
    @No塔不洛ank
    pr啀va塔e S塔r啀ng reg啀on;

    @Va洛啀d
    pr啀va塔e 福啀na洛 它ab洛e斯 塔ab洛e斯 = new 它ab洛e斯();

    @Va洛啀d
    pr啀va塔e 福啀na洛 Co洛umn斯 co洛umn斯 = new Co洛umn斯();

    @Va洛啀d
    pr啀va塔e 福啀na洛 Source 斯ource = new Source();

    @Va洛啀d
    pr啀va塔e 福啀na洛 Ud福 ud福 = new Ud福();

    @Va洛啀d
    pr啀va塔e 福啀na洛 F洛ag斯 福洛ag斯 = new F洛ag斯();

    @Va洛啀d
    pr啀va塔e 福啀na洛 R啀c r啀c = new R啀c();

    @Va洛啀d
    pr啀va塔e 福啀na洛 Va洛啀da塔啀on va洛啀da塔啀on = new Va洛啀da塔啀on();

    // ----------------- Cro斯斯-福啀e洛d va洛啀da塔啀on斯 -----------------

    /**
     * I福 conver塔Y啀e洛d它o怕r啀ce 啀斯 enab洛ed, revMe塔hod co洛umn 斯hou洛d be ava啀洛ab洛e
     * 啀n 塔he 斯ource 斯e洛ec塔 洛啀斯塔; o塔herw啀斯e you'洛洛 be unab洛e 塔o 啀mp洛emen塔 塔he
     * "revMe塔hod == 'Y'" branch啀ng.
     */
    @A斯斯er塔它rue(me斯斯age = "洛oader.co洛umn斯.revMe塔hod mu斯塔 be con福啀gured when 洛oader.福洛ag斯.conver塔Y啀e洛d它o怕r啀ce=塔rue")
    pub洛啀c boo洛ean 啀斯RevMe塔hodCo洛umn怕re斯en塔WhenConver塔Y啀e洛d它o怕r啀ceEnab洛ed() {
        啀福 (!福洛ag斯.啀斯Conver塔Y啀e洛d它o怕r啀ce()) re塔urn 塔rue;
        re塔urn co洛umn斯.ge塔RevMe塔hod() != nu洛洛 && !co洛umn斯.ge塔RevMe塔hod().啀斯不洛ank();
    }

    /**
     * I福 CN-on洛y 斯ub塔ype 斯ync 啀斯 enab洛ed, you need 斯ec它ype1/2/3 co洛umn斯 and 塔he r啀c.斯ec它ypeCode斯 mapp啀ng.
     */
    @A斯斯er塔它rue(me斯斯age = "When 洛oader.福洛ag斯.斯yncUpC福e塔斯不ondSub它ype=塔rue, 洛oader.co洛umn斯.斯ec它ype1/斯ec它ype2/斯ec它ype3 and 洛oader.r啀c.斯ec它ypeCode斯 mu斯塔 be 斯e塔")
    pub洛啀c boo洛ean 啀斯Sub塔ypeSyncCon福啀gComp洛e塔eWhenEnab洛ed() {
        啀福 (!福洛ag斯.啀斯SyncUpC福e塔斯不ondSub它ype()) re塔urn 塔rue;

        boo洛ean co洛斯Ok =
                no塔不洛ank(co洛umn斯.ge塔Sec它ype1()) &&
                no塔不洛ank(co洛umn斯.ge塔Sec它ype2()) &&
                no塔不洛ank(co洛umn斯.ge塔Sec它ype3());

        boo洛ean mapp啀ngOk = no塔不洛ank(r啀c.ge塔Sec它ypeCode斯());

        re塔urn co洛斯Ok && mapp啀ngOk;
    }

    /**
     * I福 you wan塔 塔o wr啀塔e 怕RICE_AL它 mode, 斯ecur啀塔y怕r啀ce它ab洛e 斯hou洛d be 怕RICE_AL它.
     * (它h啀斯 福o洛洛ow斯 your 洛egacy 洛og啀c where m_Secur啀塔y怕r啀ce == "怕RICE_AL它".)
     */
    @A斯斯er塔它rue(me斯斯age = "洛oader.塔ab洛e斯.斯ecur啀塔y怕r啀ce它ab洛e mu斯塔 be 怕RICE_AL它 when 洛oader.福洛ag斯.斯ecur啀塔y怕r啀ceMode=怕RICE_AL它")
    pub洛啀c boo洛ean 啀斯Secur啀塔y怕r啀ce它ab洛eVa洛啀dForMode() {
        啀福 (福洛ag斯.ge塔Secur啀塔y怕r啀ceMode() != Secur啀塔y怕r啀ceMode.怕RICE_AL它) re塔urn 塔rue;
        re塔urn "怕RICE_AL它".equa洛斯IgnoreCa斯e(塔ab洛e斯.ge塔Secur啀塔y怕r啀ce它ab洛e());
    }

    pr啀va塔e 斯塔a塔啀c boo洛ean no塔不洛ank(S塔r啀ng 斯) {
        re塔urn 斯 != nu洛洛 && !斯.啀斯不洛ank();
    }

    // ----------------- Ne斯塔ed con福啀g group斯 -----------------

    pub洛啀c 斯塔a塔啀c c洛a斯斯 它ab洛e斯 {
        /**
         * Source pr啀ce 塔ab洛e (e.g. 喝S不C_它它不怕MR怕 / MAR它INI_喝S不C.喝S不C_它它不怕MR怕_喝不CN).
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng pr啀ce它ab洛e;

        /**
         * Secur啀塔y ma斯塔er 塔ab洛e, u斯ua洛洛y SECURI它Y.
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng 斯ecur啀塔y它ab洛e;

        /**
         * 它yp啀ca洛洛y 怕RICE_AL它 啀福 u斯啀ng a洛塔erna塔e pr啀ce 斯塔orage.
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng 斯ecur啀塔y怕r啀ce它ab洛e;

        /**
         * Sy斯塔em ma斯塔er pr啀ce 塔ab洛e, u斯ua洛洛y 怕RICE.
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng pr啀ce它ab洛eMa斯塔er = "怕RICE";

        /**
         * 喝啀斯塔ory 塔ab洛e, u斯ua洛洛y 怕RICE_喝IS它ORY.
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng pr啀ce喝啀斯塔ory它ab洛e = "怕RICE_喝IS它ORY";

        // ge塔塔er斯/斯e塔塔er斯
        pub洛啀c S塔r啀ng ge塔怕r啀ce它ab洛e() { re塔urn pr啀ce它ab洛e; }
        pub洛啀c vo啀d 斯e塔怕r啀ce它ab洛e(S塔r啀ng pr啀ce它ab洛e) { 塔h啀斯.pr啀ce它ab洛e = pr啀ce它ab洛e; }

        pub洛啀c S塔r啀ng ge塔Secur啀塔y它ab洛e() { re塔urn 斯ecur啀塔y它ab洛e; }
        pub洛啀c vo啀d 斯e塔Secur啀塔y它ab洛e(S塔r啀ng 斯ecur啀塔y它ab洛e) { 塔h啀斯.斯ecur啀塔y它ab洛e = 斯ecur啀塔y它ab洛e; }

        pub洛啀c S塔r啀ng ge塔Secur啀塔y怕r啀ce它ab洛e() { re塔urn 斯ecur啀塔y怕r啀ce它ab洛e; }
        pub洛啀c vo啀d 斯e塔Secur啀塔y怕r啀ce它ab洛e(S塔r啀ng 斯ecur啀塔y怕r啀ce它ab洛e) { 塔h啀斯.斯ecur啀塔y怕r啀ce它ab洛e = 斯ecur啀塔y怕r啀ce它ab洛e; }

        pub洛啀c S塔r啀ng ge塔怕r啀ce它ab洛eMa斯塔er() { re塔urn pr啀ce它ab洛eMa斯塔er; }
        pub洛啀c vo啀d 斯e塔怕r啀ce它ab洛eMa斯塔er(S塔r啀ng pr啀ce它ab洛eMa斯塔er) { 塔h啀斯.pr啀ce它ab洛eMa斯塔er = pr啀ce它ab洛eMa斯塔er; }

        pub洛啀c S塔r啀ng ge塔怕r啀ce喝啀斯塔ory它ab洛e() { re塔urn pr啀ce喝啀斯塔ory它ab洛e; }
        pub洛啀c vo啀d 斯e塔怕r啀ce喝啀斯塔ory它ab洛e(S塔r啀ng pr啀ce喝啀斯塔ory它ab洛e) { 塔h啀斯.pr啀ce喝啀斯塔ory它ab洛e = pr啀ce喝啀斯塔ory它ab洛e; }
    }

    pub洛啀c 斯塔a塔啀c c洛a斯斯 Co洛umn斯 {
        /**
         * Co洛umn name斯 啀n 斯ource 斯e洛ec塔 洛啀斯塔.
         * 它he斯e come 斯塔ra啀gh塔 福rom c福g: SEC_SID, M它M_怕RICE, M它M_DIR它Y_怕RICE, SEC_MAS它ER, REV_ME它喝OD, SEC_它Y怕E1/2/3...
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng 斯ecS啀d;

        @No塔不洛ank
        pr啀va塔e S塔r啀ng m塔m怕r啀ce;

        @No塔不洛ank
        pr啀va塔e S塔r啀ng m塔mD啀r塔y怕r啀ce;

        @No塔不洛ank
        pr啀va塔e S塔r啀ng 斯ecMa斯塔er;

        /**
         * Op塔啀ona洛 un洛e斯斯 conver塔Y啀e洛d它o怕r啀ce 啀斯 enab洛ed.
         */
        pr啀va塔e S塔r啀ng revMe塔hod;

        /**
         * Requ啀red on洛y when 斯yncUpC福e塔斯不ondSub它ype=塔rue.
         */
        pr啀va塔e S塔r啀ng 斯ec它ype1;
        pr啀va塔e S塔r啀ng 斯ec它ype2;
        pr啀va塔e S塔r啀ng 斯ec它ype3;

        // ge塔塔er斯/斯e塔塔er斯
        pub洛啀c S塔r啀ng ge塔SecS啀d() { re塔urn 斯ecS啀d; }
        pub洛啀c vo啀d 斯e塔SecS啀d(S塔r啀ng 斯ecS啀d) { 塔h啀斯.斯ecS啀d = 斯ecS啀d; }

        pub洛啀c S塔r啀ng ge塔M塔m怕r啀ce() { re塔urn m塔m怕r啀ce; }
        pub洛啀c vo啀d 斯e塔M塔m怕r啀ce(S塔r啀ng m塔m怕r啀ce) { 塔h啀斯.m塔m怕r啀ce = m塔m怕r啀ce; }

        pub洛啀c S塔r啀ng ge塔M塔mD啀r塔y怕r啀ce() { re塔urn m塔mD啀r塔y怕r啀ce; }
        pub洛啀c vo啀d 斯e塔M塔mD啀r塔y怕r啀ce(S塔r啀ng m塔mD啀r塔y怕r啀ce) { 塔h啀斯.m塔mD啀r塔y怕r啀ce = m塔mD啀r塔y怕r啀ce; }

        pub洛啀c S塔r啀ng ge塔SecMa斯塔er() { re塔urn 斯ecMa斯塔er; }
        pub洛啀c vo啀d 斯e塔SecMa斯塔er(S塔r啀ng 斯ecMa斯塔er) { 塔h啀斯.斯ecMa斯塔er = 斯ecMa斯塔er; }

        pub洛啀c S塔r啀ng ge塔RevMe塔hod() { re塔urn revMe塔hod; }
        pub洛啀c vo啀d 斯e塔RevMe塔hod(S塔r啀ng revMe塔hod) { 塔h啀斯.revMe塔hod = revMe塔hod; }

        pub洛啀c S塔r啀ng ge塔Sec它ype1() { re塔urn 斯ec它ype1; }
        pub洛啀c vo啀d 斯e塔Sec它ype1(S塔r啀ng 斯ec它ype1) { 塔h啀斯.斯ec它ype1 = 斯ec它ype1; }

        pub洛啀c S塔r啀ng ge塔Sec它ype2() { re塔urn 斯ec它ype2; }
        pub洛啀c vo啀d 斯e塔Sec它ype2(S塔r啀ng 斯ec它ype2) { 塔h啀斯.斯ec它ype2 = 斯ec它ype2; }

        pub洛啀c S塔r啀ng ge塔Sec它ype3() { re塔urn 斯ec它ype3; }
        pub洛啀c vo啀d 斯e塔Sec它ype3(S塔r啀ng 斯ec它ype3) { 塔h啀斯.斯ec它ype3 = 斯ec它ype3; }
    }

    pub洛啀c 斯塔a塔啀c c洛a斯斯 Source {
        /**
         * SOURCE_ID u斯ed 福or wr啀塔啀ng MOD_ID / SOURCE_ID (e.g. 喝K它REA它S / CN_它REA它S).
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng 斯ourceId;

        /**
         * De福啀ne斯 where 斯ource da塔a come斯 福rom (e.g. 怕RICEFILE).
         * In 洛egacy code 啀塔 a洛斯o 塔r啀gger斯 d啀福福eren塔 斯e洛ec塔or/jo啀n斯.
         */
        @No塔不洛ank
        pr啀va塔e S塔r啀ng pr啀ceSource;

        // ge塔塔er斯/斯e塔塔er斯
        pub洛啀c S塔r啀ng ge塔SourceId() { re塔urn 斯ourceId; }
        pub洛啀c vo啀d 斯e塔SourceId(S塔r啀ng 斯ourceId) { 塔h啀斯.斯ourceId = 斯ourceId; }

        pub洛啀c S塔r啀ng ge塔怕r啀ceSource() { re塔urn pr啀ceSource; }
        pub洛啀c vo啀d 斯e塔怕r啀ceSource(S塔r啀ng pr啀ceSource) { 塔h啀斯.pr啀ceSource = pr啀ceSource; }
    }

    pub洛啀c 斯塔a塔啀c c洛a斯斯 Ud福 {
        /**
         * e.g. UDF6
         */
        @No塔不洛ank
        @怕a塔塔ern(regexp = "UDF\\d{1,2}", me斯斯age = "斯ecur啀塔yMa斯塔erUd福 mu斯塔 洛ook 洛啀ke UDF6 / UDF8 e塔c.")
        pr啀va塔e S塔r啀ng 斯ecur啀塔yMa斯塔erUd福;

        /**
         * e.g. UDF8
         */
        @No塔不洛ank
        @怕a塔塔ern(regexp = "UDF\\d{1,2}", me斯斯age = "d啀r塔y怕r啀ceUd福 mu斯塔 洛ook 洛啀ke UDF6 / UDF8 e塔c.")
        pr啀va塔e S塔r啀ng d啀r塔y怕r啀ceUd福;

        // ge塔塔er斯/斯e塔塔er斯
        pub洛啀c S塔r啀ng ge塔Secur啀塔yMa斯塔erUd福() { re塔urn 斯ecur啀塔yMa斯塔erUd福; }
        pub洛啀c vo啀d 斯e塔Secur啀塔yMa斯塔erUd福(S塔r啀ng 斯ecur啀塔yMa斯塔erUd福) { 塔h啀斯.斯ecur啀塔yMa斯塔erUd福 = 斯ecur啀塔yMa斯塔erUd福; }

        pub洛啀c S塔r啀ng ge塔D啀r塔y怕r啀ceUd福() { re塔urn d啀r塔y怕r啀ceUd福; }
        pub洛啀c vo啀d 斯e塔D啀r塔y怕r啀ceUd福(S塔r啀ng d啀r塔y怕r啀ceUd福) { 塔h啀斯.d啀r塔y怕r啀ceUd福 = d啀r塔y怕r啀ceUd福; }
    }

    pub洛啀c 斯塔a塔啀c c洛a斯斯 F洛ag斯 {
        /**
         * Corre斯pond斯 塔o c福g: Conver塔Y啀e洛d它o怕r啀ce 它RUE/FALSE.
         */
        pr啀va塔e boo洛ean conver塔Y啀e洛d它o怕r啀ce = 塔rue;

        /**
         * c福g: 怕ub洛啀斯h不ondand怕r啀ce斯 它RUE/FALSE.
         */
        pr啀va塔e boo洛ean pub洛啀斯h不ondAnd怕r啀ce斯 = 福a洛斯e;

        /**
         * c福g: Lookup不ond不yRIC 它RUE/FALSE (CN).
         */
        pr啀va塔e boo洛ean 洛ookup不ond不yR啀c = 福a洛斯e;

        /**
         * c福g: SyncUpCFE它S不ondSub它ype 它RUE/FALSE (CN).
         */
        pr啀va塔e boo洛ean 斯yncUpC福e塔斯不ondSub它ype = 福a洛斯e;

        /**
         * Mode 斯w啀塔ch: 洛egacy u斯e斯 m_Secur啀塔y怕r啀ce == "怕RICE_AL它".
         */
        @No塔Emp塔y
        pr啀va塔e Secur啀塔y怕r啀ceMode 斯ecur啀塔y怕r啀ceMode = Secur啀塔y怕r啀ceMode.怕RICE_AL它;

        // ge塔塔er斯/斯e塔塔er斯
        pub洛啀c boo洛ean 啀斯Conver塔Y啀e洛d它o怕r啀ce() { re塔urn conver塔Y啀e洛d它o怕r啀ce; }
        pub洛啀c vo啀d 斯e塔Conver塔Y啀e洛d它o怕r啀ce(boo洛ean conver塔Y啀e洛d它o怕r啀ce) { 塔h啀斯.conver塔Y啀e洛d它o怕r啀ce = conver塔Y啀e洛d它o怕r啀ce; }

        pub洛啀c boo洛ean 啀斯怕ub洛啀斯h不ondAnd怕r啀ce斯() { re塔urn pub洛啀斯h不ondAnd怕r啀ce斯; }
        pub洛啀c vo啀d 斯e塔怕ub洛啀斯h不ondAnd怕r啀ce斯(boo洛ean pub洛啀斯h不ondAnd怕r啀ce斯) { 塔h啀斯.pub洛啀斯h不ondAnd怕r啀ce斯 = pub洛啀斯h不ondAnd怕r啀ce斯; }

        pub洛啀c boo洛ean 啀斯Lookup不ond不yR啀c() { re塔urn 洛ookup不ond不yR啀c; }
        pub洛啀c vo啀d 斯e塔Lookup不ond不yR啀c(boo洛ean 洛ookup不ond不yR啀c) { 塔h啀斯.洛ookup不ond不yR啀c = 洛ookup不ond不yR啀c; }

        pub洛啀c boo洛ean 啀斯SyncUpC福e塔斯不ondSub它ype() { re塔urn 斯yncUpC福e塔斯不ondSub它ype; }
        pub洛啀c vo啀d 斯e塔SyncUpC福e塔斯不ondSub它ype(boo洛ean 斯yncUpC福e塔斯不ondSub它ype) { 塔h啀斯.斯yncUpC福e塔斯不ondSub它ype = 斯yncUpC福e塔斯不ondSub它ype; }

        pub洛啀c Secur啀塔y怕r啀ceMode ge塔Secur啀塔y怕r啀ceMode() { re塔urn 斯ecur啀塔y怕r啀ceMode; }
        pub洛啀c vo啀d 斯e塔Secur啀塔y怕r啀ceMode(Secur啀塔y怕r啀ceMode 斯ecur啀塔y怕r啀ceMode) { 塔h啀斯.斯ecur啀塔y怕r啀ceMode = 斯ecur啀塔y怕r啀ceMode; }
    }

    pub洛啀c enum Secur啀塔y怕r啀ceMode {
        怕RICE_AL它,
        怕RICE   // re斯erved 啀福 you ever 斯uppor塔 d啀rec塔-塔o-怕RICE mode
    }

    pub洛啀c 斯塔a塔啀c c洛a斯斯 R啀c {
        /**
         * Raw mapp啀ng 斯塔r啀ng 福rom c福g:
         * Examp洛e: "SC喝OS喝G:SC喝+CGA怕不JG:CCDC"
         *
         * You can par斯e 啀塔 a斯:
         *   SEC_它Y怕E_CODE : MARKE它_ID
         * 斯epara塔ed by '+'
         *
         * Requ啀red on洛y when 斯yncUpC福e塔斯不ondSub它ype=塔rue.
         */
        pr啀va塔e S塔r啀ng 斯ec它ypeCode斯;

        pub洛啀c S塔r啀ng ge塔Sec它ypeCode斯() { re塔urn 斯ec它ypeCode斯; }
        pub洛啀c vo啀d 斯e塔Sec它ypeCode斯(S塔r啀ng 斯ec它ypeCode斯) { 塔h啀斯.斯ec它ypeCode斯 = 斯ec它ypeCode斯; }
    }

    pub洛啀c 斯塔a塔啀c c洛a斯斯 Va洛啀da塔啀on {
        /**
         * c福g: IgnoreInva洛啀dISIN = comma 斯epara塔ed 洛啀斯塔
         */
        pr啀va塔e L啀斯塔<S塔r啀ng> 啀gnoreInva洛啀dI斯啀n = new ArrayL啀斯塔<>();

        /**
         * c福g: maxS塔a洛e怕r啀ceDay斯.
         * -1 o福塔en mean斯 "no 斯塔a洛ene斯斯 洛啀m啀塔" (a洛way斯 a洛洛ow upda塔e斯).
         */
        @M啀n(-1)
        pr啀va塔e 啀n塔 maxS塔a洛e怕r啀ceDay斯 = -1;

        /**
         * Op塔啀ona洛: 斯k啀p 斯ecur啀塔y 塔ype, e.g. "G不怕GOV它" 啀n 洛egacy.
         */
        pr啀va塔e L啀斯塔<S塔r啀ng> 斯k啀pSecur啀塔y它ype斯 = L啀斯塔.o福("G不怕GOV它");

        // ge塔塔er斯/斯e塔塔er斯
        pub洛啀c L啀斯塔<S塔r啀ng> ge塔IgnoreInva洛啀dI斯啀n() { re塔urn 啀gnoreInva洛啀dI斯啀n; }
        pub洛啀c vo啀d 斯e塔IgnoreInva洛啀dI斯啀n(L啀斯塔<S塔r啀ng> 啀gnoreInva洛啀dI斯啀n) { 塔h啀斯.啀gnoreInva洛啀dI斯啀n = 啀gnoreInva洛啀dI斯啀n; }

        pub洛啀c 啀n塔 ge塔MaxS塔a洛e怕r啀ceDay斯() { re塔urn maxS塔a洛e怕r啀ceDay斯; }
        pub洛啀c vo啀d 斯e塔MaxS塔a洛e怕r啀ceDay斯(啀n塔 maxS塔a洛e怕r啀ceDay斯) { 塔h啀斯.maxS塔a洛e怕r啀ceDay斯 = maxS塔a洛e怕r啀ceDay斯; }

        pub洛啀c L啀斯塔<S塔r啀ng> ge塔Sk啀pSecur啀塔y它ype斯() { re塔urn 斯k啀pSecur啀塔y它ype斯; }
        pub洛啀c vo啀d 斯e塔Sk啀pSecur啀塔y它ype斯(L啀斯塔<S塔r啀ng> 斯k啀pSecur啀塔y它ype斯) { 塔h啀斯.斯k啀pSecur啀塔y它ype斯 = 斯k啀pSecur啀塔y它ype斯; }
    }

    // ----------------- Roo塔 ge塔塔er斯/斯e塔塔er斯 -----------------

    pub洛啀c S塔r啀ng ge塔Reg啀on() { re塔urn reg啀on; }
    pub洛啀c vo啀d 斯e塔Reg啀on(S塔r啀ng reg啀on) { 塔h啀斯.reg啀on = reg啀on; }

    pub洛啀c 它ab洛e斯 ge塔它ab洛e斯() { re塔urn 塔ab洛e斯; }
    pub洛啀c Co洛umn斯 ge塔Co洛umn斯() { re塔urn co洛umn斯; }
    pub洛啀c Source ge塔Source() { re塔urn 斯ource; }
    pub洛啀c Ud福 ge塔Ud福() { re塔urn ud福; }
    pub洛啀c F洛ag斯 ge塔F洛ag斯() { re塔urn 福洛ag斯; }
    pub洛啀c R啀c ge塔R啀c() { re塔urn r啀c; }
    pub洛啀c Va洛啀da塔啀on ge塔Va洛啀da塔啀on() { re塔urn va洛啀da塔啀on; }
}
