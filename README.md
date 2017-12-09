﻿# LMJS-Lukuvinkkikirjasto



### Käyttöohje

#### Web-versio
Ohjelma sijaitsee herokussa nimellä [lmjs-lukuvinkkikirjasto](https://lmjs-lukuvinkkikirjasto.herokuapp.com).

Etusivulla on listattuna kaikki lisätyt lukuvinkit. Vinkkejä voi lisätä antamalla sivun alalaidassa oleviin tekstikenttiin ainakin kirjan nimen ja kirjailijan ja painamalla sitten "Lisää!"-nappia. Vinkin nimeä klikkaamalla pääsee tarkastelemaan yksittäistä vinkkiä. Tässä näkymässä voi myös muokata vinkin tietoja ja merkata vinkin luetuksi/lukemattomaksi. Painamalla "Tallenna muutokset"-painiketta tehdyt muutokset tallentuvat järjestelmään. "Kaikki vinkit"-napilla voi palata takaisin vinkkien listausnäkymään. 

#### Komentoriviversio
Ohjelman voi ottaa käyttöön kloonamalla sen GitHub-repositoriosta tai käynnistämällä [jar-tiedoston](https://github.com/Sadelise/LMJS-Lukuvinkkikirjasto/blob/master/LMJS-Lukuvinkkikirjasto.jar). Jar-tiedoston voi käynnistää jar-tiedoston sisältämässä kansiossa käyttämällä komentoa _java -jar LMJS-Lukuvinkkikirjasto.jar_.

Ohjelmaa käytetään komentoriviltä. Käyttäjä näkee kaikki käytettävissä olevat komennot kirjoittamalla "commands" ja painamalla Enteriä. Käyttäjä voi sitten käyttää haluamiaan komentoja kirjoittamalla niitä kysyttäessä ja painaessaan sen jälkeen Enter-näppäintä.

Ohjelmasta voi poistua "quit"-komennolla.


### Definition of done

* User storyille on määritelty hyväksymäkriteerit, jotka dokumentoidaan Cucumber-featureiksi. [Linkki featureihin](https://github.com/Sadelise/LMJS-Lukuvinkkikirjasto/tree/master/src/test/resources/lukuvinkkikirjasto)
* Asiakas pääsee näkemään koko ajan koodin ja testien tilanteen CI-palvelimelta
* Luokat/metodit/muuttujat nimetty järkevästi
* Selkeä ja perusteltu arkkitehtuuri

### Backlog
[Linkki Backlogiin](https://docs.google.com/spreadsheets/d/1OgjUlsgwDmvzZTyIIeAkLIftJII5E7hFcgInnRusNN4/edit#gid=1257881806)

### Travis
[![Build Status](https://travis-ci.org/Sadelise/LMJS-Lukuvinkkikirjasto.svg?branch=master)](https://travis-ci.org/Sadelise/LMJS-Lukuvinkkikirjasto)

[Linkki Travisiin](https://travis-ci.org/Sadelise/LMJS-Lukuvinkkikirjasto?utm_source=email&utm_medium=notification)

### Codecov
[![codecov](https://codecov.io/gh/Sadelise/LMJS-Lukuvinkkikirjasto/branch/master/graph/badge.svg)](https://codecov.io/gh/Sadelise/LMJS-Lukuvinkkikirjasto)

[Codecov testiraportointi](https://codecov.io/gh/Sadelise/LMJS-Lukuvinkkikirjasto)

