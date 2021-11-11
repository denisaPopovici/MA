import '../drugs/domain/Drug.dart';
import 'package:flutter/material.dart';
import '../drugs/repo/DrugsRepository.dart';
import '../drugs/DetailsScreen.dart';
import '../drugs/AddDrugScreen.dart';


class DrugsScreen extends StatelessWidget {

  final drugRepository = DrugsRepository();

  final List<Drug> items;


  DrugsScreen({Key? key, required this.items}) : super(key: key);


  @override
  Widget build(BuildContext context) {
    const title = 'Drug list:';

    return MaterialApp(
      title: title,
      home: Scaffold(
        appBar: AppBar(
          title: const Text(title),
        ),
        body: ListView.builder(
          // Let the ListView know how many items it needs to build.
          itemCount: items.length,
          // Provide a builder function. This is where the magic happens.
          // Convert each item into a widget based on the type of item it is.
          itemBuilder: (context, index) => GestureDetector(
            onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) => DetailsScreen(current: items[index])));
            },
            child : Card(
                child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween, 
                    children: <Widget> [
                        Column( children: <Widget>[

                            Text(items[index].getName(), 
                            style: TextStyle(
                                fontWeight: FontWeight.bold,
                                fontSize: 25.0)
                            ),

                            ],
                        ), 
                    Column( children: <Widget>[

                            Text(items[index].getQuantity().toString(),
                            style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 25.0)
                            ),

                            ],
                        ),   
                    ],    
                ),
            ),
          ),
        ),
        floatingActionButton: FloatingActionButton(
        onPressed: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) => AddDrugScreen()));

        },
        child: const Icon(Icons.add_circle_outlined),
        backgroundColor: Colors.blue,
        ),
      ),
    );
  }
}