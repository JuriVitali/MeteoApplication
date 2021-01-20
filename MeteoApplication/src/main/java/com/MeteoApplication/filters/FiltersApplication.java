package com.MeteoApplication.filters;

import com.MeteoApplication.model.Statistics;
import com.MeteoApplication.model.Data;
import com.MeteoApplication.model.Record;

import java.util.Vector;

//interfaccia che racchiude tutti i metodi per applicare i filtri
interface FiltersApplication {
	
	//filtri su data
	Vector<Record> DateFilter_gte(Vector<Record> v, Data param);
	Vector<Record> DateFilter_gt(Vector<Record> v, Data param);
	Vector<Record> DateFilter_bt(Vector<Record> v, Data inizio, Data fine);
	Vector<Record> DateFilter_eq(Vector<Record> v, Data param);
	
	//filtri per temperatura
	Vector<Record> TempFilter_gt(Vector<Record> record, double TempPar);
	Vector<Record> TempFilter_less(Vector<Record> record, double TempPar);  
	Vector<Record> TempFilter_bt(Vector<Record> record, double TempPar1, double TempPar2);
	
	//filtri per temperatura percepita
	Vector<Record> TempPerFilter_gt(Vector<Record> record, double TempPar);
	Vector<Record> TempPerFilter_less(Vector<Record> record, double TempPar);  
	Vector<Record> TempPerFilter_bt(Vector<Record> record, double TempPar1, double TempPar2);  
	
	
	//filtri sulle statistiche
	//compreso
	Vector<Statistics> reTempAvgIncl (Vector<Statistics> v, double min, double max);
	Vector<Statistics> reTempMinIncl (Vector<Statistics> v, double min, double max);
	Vector<Statistics> reTempMaxIncl (Vector<Statistics> v, double min, double max);
	Vector<Statistics> reTempVarIncl (Vector<Statistics> v, double min, double max);
	Vector<Statistics> percTempAvgIncl (Vector<Statistics> v, double min, double max);
	Vector<Statistics> percTempMinIncl (Vector<Statistics> v, double min, double max);
	Vector<Statistics> percTempMaxIncl (Vector<Statistics> v, double min, double max);
	Vector<Statistics> percTempVarIncl (Vector<Statistics> v, double min, double max);
	
	//minore
	Vector<Statistics> reTempAvgLess (Vector<Statistics> v, double max);
	Vector<Statistics> reTempMinLess (Vector<Statistics> v, double max);
	Vector<Statistics> reTempMaxLess (Vector<Statistics> v, double max);
	Vector<Statistics> reTempVarLess (Vector<Statistics> v, double max);
	Vector<Statistics> percTempAvgLess (Vector<Statistics> v, double max);
	Vector<Statistics> percTempMinLess (Vector<Statistics> v, double max);
	Vector<Statistics> percTempMaxLess (Vector<Statistics> v, double max);
	Vector<Statistics> percTempVarLess (Vector<Statistics> v, double max);
	
	//maggiore
	Vector<Statistics> reTempAvgGreat (Vector<Statistics> v, double min);
	Vector<Statistics> reTempMinGreat (Vector<Statistics> v, double min);
	Vector<Statistics> reTempMaxGreat (Vector<Statistics> v, double min);
	Vector<Statistics> reTempVarGreat (Vector<Statistics> v, double min);
	Vector<Statistics> percTempAvgGreat (Vector<Statistics> v, double min);
	Vector<Statistics> percTempMinGreat (Vector<Statistics> v, double min);
	Vector<Statistics> percTempMaxGreat (Vector<Statistics> v, double min);
	Vector<Statistics> percTempVarGreat (Vector<Statistics> v, double min);

	
	//filtro su id
	Vector<Record> idFilter_eq(Vector<Record> v,long param);

}
