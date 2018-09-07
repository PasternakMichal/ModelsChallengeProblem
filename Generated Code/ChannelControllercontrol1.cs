using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChannelControllercontrol1 : MonoBehaviour {
	
	public Network externalComm;
	public string myName = "control1";

	void Start () {
		externalComm = new Network(8888,myName,20); // port, net ID, Container size
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
if (name == "Rover") {
	RoverScript Rover = GetComponentInChildren<RoverScript> ();
	
	// send the action name, return the reply that is generated
	string reply = Rover.translate (S);
	
	
	// if inout, then do this if in only don't 
	if (reply.Equals("") == false && reply != null&& S.Length>0)
		externalComm.SendMessage("Rover,"+reply+";");
	}
	}
}

	public string cut(string message){
		return message.Substring(message.IndexOf(',')+1);
	}
}
