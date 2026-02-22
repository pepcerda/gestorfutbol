INSERT INTO
    GF_PLA_SUSCRIPCIO (PLA_TIPUS, PLA_CUOTA)
VALUES ('BASIC', 9.99);

INSERT INTO
    GF_PLA_SUSCRIPCIO (PLA_TIPUS, PLA_CUOTA)
VALUES ('PREMIUM', 19.99);

INSERT INTO
    GF_PLA_SUSCRIPCIO (PLA_TIPUS, PLA_CUOTA)
VALUES ('ENTERPRISE', 49.90);

INSERT INTO
    GF_TENANT (
        TEN_PLA,
        TEN_NOM,
        TEN_ESTAT,
        TEN_FCREA
    )
VALUES (
        1,
        'Athletic Club Tenant',
        'ACTIU',
        CURRENT_TIMESTAMP
    );

-- SUPERADMIN
INSERT INTO
    GF_USUARI (
        USU_PROVID,
        USU_SUPERADMIN,
        USU_NOM,
        USU_EMAIL
    )
VALUES (
        'kp_601dc5f9ec944428aa3708faf30c3dfe',
        true,
        'Josep Cerda',
        'josep.cerda.roscar@gmail.com'
    );

INSERT INTO
    GF_USUARI_TENANT (
        UT_USU_ID,
        UT_TEN_ID,
        UT_ROL,
        UT_ACTIU
    )
VALUES (1, 1, 'ADMINISTRADOR', true);

INSERT INTO GF_ROLDIR (RDI_TEN, RDI_ROL) VALUES (1, 'President');

INSERT INTO
    GF_ROLDIR (RDI_TEN, RDI_ROL)
VALUES (1, 'Vicepresident I');

INSERT INTO GF_ROLDIR (RDI_TEN, RDI_ROL) VALUES (1, 'Secretari');

INSERT INTO GF_ROLDIR (RDI_TEN, RDI_ROL) VALUES (1, 'Tresorer');

INSERT INTO GF_ROLDIR (RDI_TEN, RDI_ROL) VALUES (1, 'Vocal');

INSERT INTO
    GF_CONFIG (
        CONF_ID,
        CONF_TEN,
        CONF_CIF,
        CONF_NOM,
        CONF_COL,
        CONF_CLF1,
        CONF_CLF2
    )
VALUES (
        1,
        1,
        'G57861692',
        'Athletic Club Montuiri',
        '#000000',
        '#ee4f4f',
        '#e8d1d1'
    );

INSERT INTO
    GF_CAMPANYA (CAM_TEN, CAM_ANY, CAM_TITOL)
VALUES (
        1,
        '2025-01-01',
        'Temporada 2025-2026'
    );

INSERT INTO
    GF_TIPSOCI (TIS_CAM, TIS_NOM, TIS_IMP)
VALUES (1, 'Tipo Soci 1', 50);

INSERT INTO
    GF_TIPSOCI (TIS_CAM, TIS_NOM, TIS_IMP)
VALUES (1, 'Tipo Soci 2', 75);

INSERT INTO
    GF_CATEGORIA (CAT_CAM, CAT_NOM)
VALUES (
        1,
        'PREFERENTE REGIONAL MALLORCA'
    );

INSERT INTO
    GF_EQUIP (EQP_CAT, EQP_NOM, EQP_QUOTA)
VALUES (
        1,
        'ATHLETIC CLUB MONTUIRI',
        0
    );

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2025, 12, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2025, 11, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2025, 10, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2025, 9, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2025, 8, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2026, 6, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2025, 7, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2026, 5, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2026, 4, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2026, 3, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2026, 2, 1, 1);

INSERT INTO
    GF_MENSUALITAT (
        MEN_ANY,
        MEN_MES,
        MEN_CAM,
        MEN_EQP
    )
VALUES (2026, 1, 1, 1);
-- Script de inserción de jugadores con licencia tipo A compatible con H2
-- Generado automáticamente el 2025-09-09
-- Inserciones en GF_MEMPLANT y GF_JUGADOR respetando la herencia
-- MEM_ID se autogenera, se usa subconsulta para obtener el último ID

INSERT INTO GF_POS_JUGADOR (POS_TEN, POS_NOM) VALUES (1, 'PORTER');

INSERT INTO GF_POS_JUGADOR (POS_TEN, POS_NOM) VALUES (1, 'DEFENSA');

INSERT INTO GF_POS_JUGADOR (POS_TEN, POS_NOM) VALUES (1, 'MIGCAMPISTA');

INSERT INTO GF_POS_JUGADOR (POS_TEN, POS_NOM) VALUES (1, 'DAVANTER');

-- Inicio de inserciones

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'ANTONI',
        'LLULL',
        'PLOMER',
        '2002-07-19',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        4
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'RAFEL',
        'MAGRO',
        'PERELLO',
        '2002-01-31',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        4
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'JOAN',
        'MANERA',
        'GARAU',
        '2002-07-15',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        3
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'JOSE LUIS',
        'PUIGSERVER',
        'TOLENTINO',
        '2006-09-29',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        3
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'MARC',
        'SANSÓ',
        'SANCHEZ',
        '2005-09-25',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'DIDAC',
        'FARRERAS',
        'SERRAT',
        '1998-09-16',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        3
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'MIGUEL A',
        'ALVAREZ',
        'GONZALEZ',
        '1985-12-06',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'MIQUEL',
        'BAUZA',
        'BARCELO',
        '1998-02-19',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        3
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'JOSEP',
        'BAUZA',
        'POCOVI',
        '2002-05-28',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        3
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'JOSE',
        'CASTIZO',
        'GIL',
        '1993-01-14',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'SERGIO',
        'CAVA',
        'MATIES',
        '2005-09-26',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        4
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'BALTASAR',
        'CERDA',
        'GARCIAS',
        '2000-01-31',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'MIQUEL',
        'FLORIT',
        'RIGO',
        '2000-02-09',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        4
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'MIQUEL',
        'GINARD',
        'FIOL',
        '1994-11-05',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        4
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'MIQUEL',
        'MARTORELL',
        'BAUZA',
        '2001-05-09',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'PERE J',
        'MARTORELL',
        'BAUZA',
        '1997-01-23',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'GUILLERMO',
        'MAS',
        'JAUME',
        '2001-06-10',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'ANTONI',
        'MAYOL',
        'ARBONA',
        '1992-02-27',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        3
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'BENET RAMON',
        'MOLL',
        'CAMPINS',
        '2000-10-06',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        1
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'MIQUEL',
        'SAMANIEGO',
        'ANDREU',
        '2004-02-23',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        2
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'BARTOMEU',
        'SASTRE',
        'MULET',
        '1994-09-27',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        3
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

INSERT INTO
    GF_MEMPLANT (
        MEM_CAM,
        MEM_EQP,
        MEM_NOM,
        MEM_LLIN1,
        MEM_LLIN2,
        MEM_DNAIX,
        MEM_ROL
    )
VALUES (
        1,
        1,
        'RACHID',
        'ZOUGGAGHI',
        'ZOUGGAGH',
        '1997-08-24',
        'GF_JUGADOR'
    );

INSERT INTO
    GF_JUGADOR (MEM_ID, JUG_POSICIO)
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        1
    );

INSERT INTO
    GF_QUOTA_JUGADOR (
        QUO_JUG,
        QUO_ESTAT,
        QUO_EXEP,
        QUO_QUANT
    )
VALUES (
        (
            SELECT MAX(MEM_ID)
            FROM GF_MEMPLANT
        ),
        'PENDENT',
        false,
        0
    );

-- Fin del script

-- Plantilla HTML
INSERT INTO
    GF_PLANTILLA_DOCUMENTO (
        PLA_TEN,
        PLA_COD,
        PLA_NOM,
        PLA_IDI,
        PLA_HTML,
        PLA_CREA,
        PLA_ACT
    )
VALUES (
        1,
        'REBUT_PATROCINI',
        'Rebut de donatiu',
        'ca',
        '
  <p><span style="font-family: arial, helvetica, sans-serif;"><!-- Capçalera --></span></p>
<header class="header">
<table style="border-collapse: collapse; width: 99.0593%; height: 111px;" border="0"><colgroup><col style="width: 12.1867%;"><col style="width: 87.8133%;"></colgroup>
<tbody>
<tr style="height: 110px;">
<td><img src="data:image/png;base64,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" width="137" height="157"></td>
<td>
<h2 class="club-name" style="text-align: center;"><span style="font-family: arial, helvetica, sans-serif;">ATHLETIC CLUB MONTU&Iuml;RI</span></h2>
</td>
</tr>
</tbody>
</table>
<div class="club-name">&nbsp;</div>
<div class="doc-title"><span style="font-family: arial, helvetica, sans-serif;">Rebut de donatiu&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<strong>Data:</strong> {{dataDonacio}}</span></div>
</header>
<p><span style="font-family: arial, helvetica, sans-serif;"><!-- Cos principal --></span></p>
<section class="panel">
<p><span style="font-family: arial, helvetica, sans-serif;">L''<strong>Athletic Club Montu&iuml;ri</strong> confirma que ha rebut de <strong>{{nom}}</strong> (DNI/NIF <strong>{{cif}}</strong>) la quantitat de <span class="amount">{{donacio}} &euro;</span> en concepte de patrocini per a la temporada&nbsp;<strong>{{campanya}}</strong>.</span></p>
</section>
<section class="panel">
<h3><span style="font-family: arial, helvetica, sans-serif;">Dades del donant</span></h3>
<table style="border-collapse: collapse; width: 52.0021%; height: 75.3438px;" border="1"><colgroup><col style="width: 61.088%;"><col style="width: 38.912%;"></colgroup>
<tbody>
<tr style="height: 37.1719px;">
<td><strong><span style="font-family: arial, helvetica, sans-serif;">Nom</span></strong></td>
<td><strong><span style="font-family: arial, helvetica, sans-serif;">CIF</span></strong></td>
</tr>
<tr style="height: 38.1719px;">
<td><span style="font-family: arial, helvetica, sans-serif;">{{nom}}</span></td>
<td><span style="font-family: arial, helvetica, sans-serif;">{{cif}}</span></td>
</tr>
</tbody>
</table>
<div class="row">&nbsp;</div>
<div class="row">&nbsp;</div>
</section>
<section class="panel">
<h3><span style="font-family: arial, helvetica, sans-serif;">Dades del club</span></h3>
<div class="row">&nbsp;</div>
<div class="row">
<div class="label">
<table style="border-collapse: collapse; width: 38.7028%; height: 108.516px;" border="1"><colgroup><col style="width: 45.1532%;"><col style="width: 54.8468%;"></colgroup>
<tbody>
<tr style="height: 34.1719px;">
<td><strong><span style="font-family: arial, helvetica, sans-serif;">Club</span></strong></td>
<td><span style="font-family: arial, helvetica, sans-serif;">Athletic Club Montu&iuml;ri</span></td>
</tr>
<tr style="height: 37.1719px;">
<td><strong><span style="font-family: arial, helvetica, sans-serif;">NIF</span></strong></td>
<td><span style="font-family: arial, helvetica, sans-serif;">B6478542</span></td>
</tr>
<tr style="height: 37.1719px;">
<td><strong><span style="font-family: arial, helvetica, sans-serif;">President/a</span></strong></td>
<td><span style="font-family: arial, helvetica, sans-serif;">Antoni Qui&ntilde;ones</span></td>
</tr>
</tbody>
</table>
</div>
<div class="label">&nbsp;</div>
<div class="label">
<section class="signature">
<div class="sign-box">
<div class="sign-line"><span style="font-family: arial, helvetica, sans-serif;">El club i el/la president/a</span></div>
</div>
</section>
<span style="font-family: arial, helvetica, sans-serif;"><!-- Peu de pàgina --></span><footer class="footer">
<div><span style="font-family: arial, helvetica, sans-serif;">Athletic Club Montu&iuml;ri</span></div>
</footer><span style="font-family: arial, helvetica, sans-serif;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span></div>
</div>
</section>
<section class="signature">
<div class="sign-box">&nbsp;</div>
</section>
<footer class="footer">
<div>&nbsp;</div>
</footer>',
        NOW(),
        NOW()
    );