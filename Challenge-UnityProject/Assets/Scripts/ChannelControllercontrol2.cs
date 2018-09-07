using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChannelControllercontrol2 : MonoBehaviour {
	
	public Network externalComm;
	public string myName = "control2";

	void Start () {
		//--- Load the port from config file
		int port = 8887;
		string path = Application.dataPath;
		path = path.Substring (0, path.LastIndexOf ('/'));
		path = path.Substring (0, path.LastIndexOf ('/'));
		string[] lines = System.IO.File.ReadAllLines(path+ "/Settings/config.txt");
		foreach (string line in lines) {
			if (line.Contains("leaderPort")==true){
				port = int.Parse(line.Substring(line.IndexOf('=')+1));
			}
		}
		//---
		externalComm = new Network(port,myName,20); // port, net ID, Container size
		externalComm.StartNetwork();
	}

	// Update is called once per frame
	void Update () {
		if (externalComm.isContainerEmpty () == false) {
			route(externalComm.getMessage());
		}

	}
	
	// take out the important information from the message
		public void route(string S){
			if (S != null && S.Equals("")== false && S.Length>0) {
				string name = S.Substring (0, S.IndexOf (','));
				S = cut (S);
				// send the message on to the designated recipient inout
if (name == "Target") {
	TargetScript Target = GetComponentInChildren<TargetScript> ();
	
	// send the action name, return the reply that is generated
	string reply = Target.translate (S);
	
	
	// if inout, then do this if in only don't 
	if (reply.Equals("") == false && reply != null&& S.Length>0)
		externalComm.SendMessage("Target,"+reply+";");
	}
	}
}

	public string cut(string message){
		return message.Substring(message.IndexOf(',')+1);
	}
}
