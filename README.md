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

### **Lista degli operatori applicabili per ogni campo**
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

### **Richieste disponibili**
Il client può ottenere i dati desiderati attraverso le seguenti richieste:
> **GET /metadata**

Questa richiesta consente di ottenere i metadati riguardanti gli oggetti restituiti dall'applicazione.

> **GET /data**

Rotta con cui si può visualizzare l'intero dataset.

> **GET /getId?name="sottostringa"**

Rotta che consente di ottenere gli id delle città che nel loro nome contengono "sottostringa".

> **POST /stats?id=Id**

Richiesta che consente di ottenere le statistiche sulle temperature relative alla città il cui id è Id. Tale rotta ammette un filtro sul periodo in cui vengono calcolate le statistiche.
Campi disponibili per il filtraggio: **"period"**.
Esempio:

> **POST /liveTemp**

Rotta che ammette un filtro sull'id e restituisce i dati attuali sulle temperature registrate nelle città il cui id rispetta i vincoli imposti dall'utente.
Campi disponibili per il filtraggio: **"id"**.
Esempio:

> **POST /cities** 

Rotta che permette all'utente di inserire un filtro contenente vincoli su temperatura, temperatura percepita e periodo e restituisce le città in cui in tale periodo si sono registrati almeno una volta valori che rispettano tali parametri.
Campi disponibili per il filtraggio: **"period"**,**"temperature"**,**"tempPer"**.
Esempio:

In tale esempio l'applicazione restituisce le città in dal 17-01-2021 al momento dell'ultima misurazione effettuata si è registrata almeno una volta un temperatura inferiore a -6.0 gradi Celsius.

> **POST /filterStats?periodStart="data1"&periodEnd="data2"**

Questa rotta consente di inserire un filtro su varie statistiche (riguardanti la temperatura) e restituisce l'elenco delle città le cui statistiche, calcolate tra "data1" e "data2" rispettano tale filtro.
Campi disponibili per il filtraggio: **"ReTempAvg"**,**"ReTempMax"**,**"ReTempMin"**,**"ReTempVariance"**,**"PercTempAvg"**,**"PercTempMax"**,**"PercTempMin"**,**"PercTempVariance"**.
Esempio:

### **Sviluppo dell'Applicazione**
### Diagramma dei Casi d'Uso
![NewModel Use Case Diagram](https://user-images.githubusercontent.com/75090467/105073349-3e76f400-5a87-11eb-8e7a-d636d19c8b9f.jpg)

### Package
![Package Hierarchy](https://user-images.githubusercontent.com/75090467/105076468-623c3900-5a8b-11eb-9f51-5ba25a6ad193.jpg)

### Diagramma delle Classi
* ##### Package com.MeteoApplication
    ![main](https://user-images.githubusercontent.com/75090467/105213765-65006200-5b4f-11eb-853a-3154dda9b7f1.PNG)

* ##### Package com.MeteoAppApplication.model
     ![com model Class Diagram](https://user-images.githubusercontent.com/75090467/105073730-b9400f00-5a87-11eb-9121-c7366467b537.jpg)

* ##### Package com.MeteoApplication.database
    ![com database Class Diagram](https://user-images.githubusercontent.com/75090467/105074753-17212680-5a89-11eb-9982-83fee4ef00fb.jpg)

* ##### Package com.MeteoAppApplication.controller
    ![controller](https://user-images.githubusercontent.com/75090467/105213032-75fca380-5b4e-11eb-9b0d-b68703219865.PNG)

* ##### Package com.MeteoApplication.filters
    ![com filter Class Diagram](https://user-images.githubusercontent.com/75090467/105073010-bf81bb80-5a86-11eb-99cc-41d8e33bd24e.jpg)

* ##### Package com.MeteoApplication.stats
    ![com stats Class Diagram](https://user-images.githubusercontent.com/75090467/105208114-9e819f00-5b48-11eb-8ecc-b2034ca2363d.jpg)

* ##### Package com.MeteoApplication.util
    ![dateoperation](https://user-images.githubusercontent.com/75090467/105212673-0c7c9500-5b4e-11eb-972c-a1b4ed46e6b3.PNG)

* ##### Package com.MeteoApplication.Exception
![com MeteoApplication Exception Class Diagram3](https://user-images.githubusercontent.com/75090467/105078753-be548c80-5a8e-11eb-9674-077dfeab4b34.jpg)

### Diagramma delle sequenze

* #### GET/metadata
![Metadata Sequence Diagram](https://user-images.githubusercontent.com/75083712/105418371-4e383900-5c3d-11eb-8e01-63fc1f7ac770.jpg)

* #### GET/data
![Data Sequence Diagram](https://user-images.githubusercontent.com/75083712/105418443-7162e880-5c3d-11eb-9b7e-0c61402778ae.jpg)

* #### GET/getId
![getId sequence diagram](https://user-images.githubusercontent.com/75083712/105418479-7fb10480-5c3d-11eb-906d-c4a61d0e4e80.jpg)

* #### POST/stats
![stats sequence diagram](https://user-images.githubusercontent.com/75083712/105418512-8b043000-5c3d-11eb-9ce0-e8974619b431.jpg)

* #### POST/liveTemp
![liveTemp sequence diagram](https://user-images.githubusercontent.com/75083712/105418584-a8d19500-5c3d-11eb-804b-8668443d762b.jpg)

* #### POST/cities
![cities sequence diagram](https://user-images.githubusercontent.com/75083712/105418617-b71fb100-5c3d-11eb-8bdf-e6e52b5deec4.jpg)

* #### POST/filterStats
![statsFilter sequence diagram](https://user-images.githubusercontent.com/75083712/105418720-e0404180-5c3d-11eb-8810-7d3222bad38d.jpg)

## Autori
* ##### Vitali Juri : Schedulatore, implementato il Controller, i gestori dei filtri, classi per la gestione delle eccezioni, filtri, UML Sequence Diagram
* ##### Sebastianelli Nicola : Schedulatore, implementato le classi DataManagement ,DataManagementImplementation e DateOperations, test Junit, UML Class Diagram
* ##### Palladino Roberto : implementazione filtri, test Junit, implementato StatsCalculator e BasicStatsCalculator , UML Use Case Diagram
