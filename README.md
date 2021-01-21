# **MeteoApplication**

### **Introduzione**
**MeteoApplication** consiste in una REST API che consente ad un client di ottenere informazioni relative alle temperature attuali, alle temperature che si sono registrate nei giorni passati e varie statistiche su queste ultime. Per fare ciò l'applicazione fa uso dei dati presenti in un dataset contenuto in un file JSON (Data.Json) sull'account Dropbox progettoesameoop@gmail.com. Tali dati sono stati ottenuti mediante uno schedulatore.
## **Schedulatore**
Lo schedulatore utilizzato consiste in un programma che effettua ogni ora delle chiamate all'API di OpenWeather per ottenere i dati sulle temperature registrate in diverse città. Più precisamente ogni ora vengono raccoglie informazioni sulle temperature relative alle città che sono registrate in un altro file Json (CityList.Json) sul medesimo account Dropbox.
Il dataset attualmente disponibile contiene informazioni sulle temperature registrate tra il **12-01-2021** e il **19-01-2021** nei capoluoghi di provincia delle regioni:
- **Friuli Venezia Giulia**
- **Trentino-Alto Adige**
- **Veneto**
- **Lombardia**

#### Diagramma delle classi dello schedulatore

![Scheduler Class Diagram](https://user-images.githubusercontent.com/75090467/105213502-118e1400-5b4f-11eb-8806-a2a5e11a6840.jpg)

## **MeteoApplication**
L'applicazione **MeteoApplication** consente al client di ottenere informazioni sulla temperatura e sulle sue statistiche attraverso sette diverse rotte. 
Quattro di esse consentono di applicare un filtro in formato JSON che deve avere la seguente forma:

>__{"campo": {"operatore": dato} }__

Nel caso in cui si voglia applicare filtri multipli è possibile farlo specificando nel body della richiesta un oggetto JSON con la seguente struttura:

  >__{ "operatore logico" : [{filtro1},{filtro2},...] }__

L'operatore logico ("and" o "or") è ammesso solamente all'inizio del filtro, ossia non può essere innestato.

#### **Lista degli operatori applicabili per ogni campo**
La seguente tabella mostra tutti i campi che possono essere filtrati e gli operatori che è possibile applicare a ciascuno di essi. 
|  Campo  |  Descrizione  |  Operatori disponibili  |
|---------|---------------|-------------------------|
|id| ID della città|  $eq |
|period| Data | $eq, $gt, $gte, $bt |
| temperature | Temperatura reale | $gt, $lt, $bt|
| tempPer | Temperatura percepita| $gt, $lt, $bt|
| ReTempAvg |Temperatura reale media| $gt, $lt, $bt|
| ReTempMax |Temperatura reale massima| $gt, $lt, $bt|
| ReTempMin |Temperatura reale minima| $gt, $lt, $bt|
| ReTempVariance |Varianza della temperatura reale| $gt, $lt, $bt|
| PercTempAvg|Temperatura percepita media| $gt, $lt, $bt|
| PercTempMax|Temperatura percepita massima| $gt, $lt, $bt|
| PercTempMin|Temperatura percepita minima| $gt, $lt, $bt|
| PercTempVariance|Varianza della temperatura percepita| $gt, $lt, $bt|

__Nota__ : mentre le temperature consistono in numeri a virgola mobile, il valore della data deve essere inserito in una stringa e deve avere il formato aaaa-mm-gg.
