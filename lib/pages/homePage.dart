import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _HomePageState();
  }
}

class _HomePageState extends State<HomePage> {
  // holds the list of channel images
  List channelImgList = [
    "polimer.png",
    "suntv.png",
    "mathurbhumi.png",
    "manorama.png",
    "asianet.png",

  ];

  // holds channel youtube ID
  List yotubeIDList = [
    "PDjjIYAWsrk",
    "RAyAyGy0WWk",
    "_adHc31U5V4",
    "CtQYF3wlgL8",
    "H9mXFeGsGEE"
  ];

  // holds the platform method channel
  static const platform = const MethodChannel("com.a011.gampsnews");

  Widget _gridViewBuilder() {
    return GridView.count(
      // Create a grid with 2 columns. If you change the scrollDirection to
      // horizontal, this produces 2 rows.
      crossAxisCount: 2,
      children: List.generate(5, (index) {
        return Center(
          child: InkWell(
            child: Image(
              image: AssetImage("assets/images/" + channelImgList[index]),
              height: 100,
            ),
            onTap: () {
              String result = platform.invokeMethod("playVideo", {"vidId":yotubeIDList[index]}).toString();
            },
          ),
        );
      }),
    );
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          leading: Icon(Icons.ondemand_video),
          backgroundColor: Colors.teal,
          title: Text("Gramps News"),
        ),
        body: _gridViewBuilder(),
      ),
    );
  }
}
