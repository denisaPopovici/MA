import '../drugs/domain/Drug.dart';
import 'package:flutter/material.dart';
import '../drugs/repo/DrugsRepository.dart';
import '../drugs/DrugsScreen.dart';

class UpdateDrugScreen extends StatelessWidget {

  final drugRepository = DrugsRepository();

  UpdateDrugScreen({Key? key, required this.current}) : super(key: key);
  
  final Drug current;


  final nameController = TextEditingController();
  final priceController = TextEditingController();
  final producerController = TextEditingController();
  final providerController = TextEditingController();
  final releaseYearController = TextEditingController();
  final quantityController = TextEditingController();



  @override
  Widget build(BuildContext context) {
    const title = 'Update drug';

    return MaterialApp(
      title: title,
      home: Scaffold(
        appBar: AppBar(
          title: const Text(title),
        ),
        body: Center (child : Column( children: <Widget>[ 
        TextField(
          controller: nameController, decoration: InputDecoration( border: OutlineInputBorder(), labelText: 'Name',),
        ),
        TextField(
          controller: priceController, decoration: InputDecoration( border: OutlineInputBorder(), labelText: 'Price',),
        ),
        TextField(
          controller: producerController, decoration: InputDecoration( border: OutlineInputBorder(), labelText: 'Producer',),
        ),
        TextField(
          controller: providerController, decoration: InputDecoration( border: OutlineInputBorder(), labelText: 'Provider',),
        ),
        TextField(
          controller: releaseYearController, decoration: InputDecoration( border: OutlineInputBorder(), labelText: 'Release year',),
        ),
        TextField(
          controller: quantityController, decoration: InputDecoration( border: OutlineInputBorder(), labelText: 'Quantity',),
        ),
        Row(mainAxisAlignment: MainAxisAlignment.center, 
        children: 
        <Widget>[
            TextButton(
                style: TextButton.styleFrom(
                    textStyle: const TextStyle(fontSize: 20),
                ),
                onPressed: () {
                    drugRepository.updateDrug(current.getId(), nameController.text, double.parse(priceController.text), providerController.text, producerController.text, int.parse(releaseYearController.text), int.parse(quantityController.text));
                    var list = drugRepository.getAllDrugs();
                    Navigator.push(context, MaterialPageRoute(builder: (context) => DrugsScreen(items: list)));},
                child: const Text('Update'),
                ),
            ],
        ),], 
        
        ),
        ),
      ),
      );
  }
}
