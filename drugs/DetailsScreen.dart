import '../drugs/domain/Drug.dart';
import 'package:flutter/material.dart';
import '../drugs/repo/DrugsRepository.dart';
import '../drugs/DrugsScreen.dart';
import '../drugs/UpdateDrugScreen.dart';

class DetailsScreen extends StatelessWidget {

  final drugRepository = DrugsRepository();

  DetailsScreen({Key? key, required this.current}) : super(key: key);
  final Drug current;


  @override
  Widget build(BuildContext context) {
    const title = 'Drug details';

    return MaterialApp(
      title: title,
      home: Scaffold(
        appBar: AppBar(
          title: const Text(title),
        ),
        body: Center (child : Column( children: <Widget>[ 
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: <Widget>[Text('Name:', style: TextStyle(fontSize: 22)), Text(current.getName(), style: TextStyle(fontSize: 22)),]),
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: <Widget>[Text('Price:', style: TextStyle(fontSize: 22)), Text(current.getPrice().toString(), style: TextStyle(fontSize: 22)),]),
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: <Widget>[Text('Provider:', style: TextStyle(fontSize: 22)), Text(current.getProvider(), style: TextStyle(fontSize: 22)),]),
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: <Widget>[Text('Producer:', style: TextStyle(fontSize: 22)), Text(current.getProducer(), style: TextStyle(fontSize: 22)),]),
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: <Widget>[Text('Release year:', style: TextStyle(fontSize: 22)), Text(current.getReleaseYear().toString(), style: TextStyle(fontSize: 22)),]),
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: <Widget>[Text('Quantity:', style: TextStyle(fontSize: 22)), Text(current.getQuantity().toString(), style: TextStyle(fontSize: 22)),]),
        Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, 
        children: 
        <Widget>[
            TextButton(
                style: TextButton.styleFrom(
                    textStyle: const TextStyle(fontSize: 20),
                ),
                onPressed: () {
                    showAlertDialog(context, this.drugRepository, current.getId());
                },
                child: const Text('Delete drug'),
                ),
            TextButton(
                style: TextButton.styleFrom(
                    textStyle: const TextStyle(fontSize: 20),
                ),
                onPressed: () {Navigator.push(context, MaterialPageRoute(builder: (context) => UpdateDrugScreen(current: current)));},
                child: const Text('Update drug'),
                )
            ],
        ),], 
        
        ),
        ),
      ),
      );
  }
}

showAlertDialog(BuildContext context, DrugsRepository repo, String id) {
  // set up the buttons
  Widget yesButton = FlatButton(
    child: Text("Yes"),
    onPressed:  (
         
    ) {
        Navigator.of(context).pop();
        repo.deleteDrug(id);
        var list = repo.getAllDrugs();
        Navigator.push(context, MaterialPageRoute(builder: (context) => DrugsScreen(items: list)));
    },
  );
  // set up the AlertDialog
  AlertDialog alert = AlertDialog(
    title: Text("AlertDialog"),
    content: Text("Are you sure you want to delete this drug?"),
    actions: [
      yesButton,
    ],
  );
  // show the dialog
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return alert;
    },
  );
}