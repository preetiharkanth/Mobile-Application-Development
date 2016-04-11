package com.preetiharkanth.extra.myapplication;

import android.os.Handler;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Ajay Wisawe on 2/20/2016.
 */
public class PersonUtil {



    static public class PersonsJSONParser{
        static ArrayList<Person> parsePerson(String in) throws JSONException {
            ArrayList<Person> personList =new ArrayList<Person>();

            JSONObject root = new JSONObject(in);
            JSONArray personArray = root.getJSONArray("persons");
            for(int i=0;i<personArray.length();i++){
                JSONObject personJSONObject = personArray.getJSONObject(i);

                Person person = Person.createPerson(personJSONObject);
                /*Person person = new Person();
                person.setName(personJSONObject.getString("name"));
                person.setId(personJSONObject.getInt("id"));
                person.setDepartment(personJSONObject.getString("department"));
                person.setAge(personJSONObject.getInt("age"));*/
                personList.add(person);

            }
            return personList;
        }
    }



    /*static public class PersonPullParser extends DefaultHandler{

        //ArrayList<Person> personList;
        //Person person;

        static  ArrayList<Person> parsePerson(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            Person person = null;
            ArrayList<Person> personList = new ArrayList<Person>();
            int event = parser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT){
               switch (event){
                   case XmlPullParser.START_TAG:
                       if(parser.getName().equals("person")){
                           person = new Person();
                           person.setId(parser.getAttributeValue(null,"id"));
                       }else if(parser.getName().equals("name")){
                           person.setName(parser.nextText().trim());
                       }else if(parser.getName().equals("age")){
                           person.setAge(parser.nextText().trim());
                       }else if(parser.getName().equals("department")){
                           person.setDepartment(parser.nextText().trim());
                       }
                       break;
                   default:
                       break;
                   case XmlPullParser.END_TAG:
                       if(parser.getName().equals("person")){
                           personList.add(person);
                           person = null;
                       }
               }


                event = parser.next();
            }
            return personList;
        }
    }




        static public class PersonSAXParser extends DefaultHandler{

        //ArrayList<Person> personList;
        //Person person;
        StringBuilder xmlInnerText;

        public ArrayList<Person> getPersonList() {
            return personList;
        }

        public void setPersonList(ArrayList<Person> personList) {
            this.personList = personList;
        }

        static public ArrayList<Person> parsePerson(InputStream in) throws IOException, SAXException {
            PersonSAXParser parser = new PersonSAXParser();
            Xml.parse(in,Xml.Encoding.UTF_8,parser);

            return parser.getPersonList();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            xmlInnerText = new StringBuilder();
            personList = new ArrayList<Person>();

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("person")){
                person = new Person();
                person.setId(attributes.getValue("id"));
            }
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("person")){
                personList.add(person);
            }else if(localName.equals("name")){
                person.setName(xmlInnerText.toString().trim());
            }else if(localName.equals("age")){
                person.setAge(xmlInnerText.toString().trim());
            }else if(localName.equals("department")){
                person.setDepartment(xmlInnerText.toString().trim());
            }
            xmlInnerText.setLength(0);
        }



        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnerText.append(ch,start,length);
        }


        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

    }*/
}
