import '../domain/Drug.dart';
import 'package:flutter/material.dart';

class DrugsRepository {

    List<Drug> drugList = [Drug('unu', 'Algocalmin', 28, 'MockProvider', 'MockProducer', 2008, 210),Drug("doi", "Ibuprofen", 16, "MockProvider", "MockProducer", 2000, 0),
     Drug("trei","Nurofen", 16, "MockProvider", "MockProducer", 2001, 35), Drug("patru", "Paracetamol", 13, "MockProvider", "MockProducer", 2007, 43),
     Drug("cinci", "Strepsils", 3, "MockProvider", "MockProducer", 2007, 21), Drug("sase", "Bepanthen", 30, "MockProvider", "MockProducer", 2017, 1), 
     Drug("sapte", "Omeran", 22, "MockProvider", "MockProducer", 2017, 18)];

    DrugsRepository(){
    }

    List<Drug> getAllDrugs(){
        return drugList;
    }

    void deleteDrug(String id){
        drugList.removeWhere((item) => item.getId() == id);
    }

    void updateDrug(String id, String nume, double price, String provider, String producer, int releaseYear, int quantity){
        for(Drug d in drugList){
            if(d.getId() == id){
                d.setName(nume);
                d.setPrice(price);
                d.setProvider(provider);
                d.setProducer(producer);
                d.setReleaseYear(releaseYear);
                d.setQuantity(quantity);
            }
        }
    }

    void addDrug(String id, String nume, double price, String provider, String producer, int releaseYear, int quantity){
        var d = Drug(id, nume, price, provider, producer, releaseYear, quantity);
        drugList.add(d);
    }


}