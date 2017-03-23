package com.droid.klo.crawler.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.droid.klo.crawler.contentProvider.DaoCP;
import com.droid.klo.crawler.db.Source;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by prpa on 3/21/17.
 */

public class JSoupMain {

    //region varibles
    private final static String TAG = "JSoupMain";
    private String url;
    private List<String> usareAgentList;
    private int userAgentNumber;
    private Context context;
    //region user agent array
    private String[] uaArray = {
            //source : https://deviceatlas.com/blog/list-of-user-agent-strings
            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",//googlebot
            "Mozilla/5.0 (compatible; bingbot/2.0; +http://www.bing.com/bingbot.htm)",//bing bot
            "Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)",//yahoo bot
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",//Windows 10-based PC using Edge browser
            "Mozilla/5.0 (X11; CrOS x86_64 8172.45.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.64 Safari/537.36",//Chrome OS-based laptop using Chrome browser (Chromebook)
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9",//Mac OS X-based computer using a Safari browser
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36", //Windows 7-based PC using a Chrome browser
            "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) Gecko/20100101 Firefox/15.0.1",//Linux-based PC using a Firefox browser
            "Mozilla/5.0 (Linux; Android 6.0.1; SM-G920V Build/MMB29K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36",//Samsung Galaxy S6
            "Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36",//HTC One M9
            "Mozilla/5.0 (Linux; Android 5.0.2; SAMSUNG SM-T550 Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/3.3 Chrome/38.0.2125.102 Safari/537.36",//Samsung Galaxy Tab A
            //https://techblog.willshouse.com/2012/01/03/most-common-user-agents/
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36",//Chrome Generic Win10
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36",//Chrome Generic Win7
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36",//Chrome Generic Win10
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36",//Chrome Generic MacOSX
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/602.4.8 (KHTML, like Gecko) Version/10.0.3 Safari/602.4.8",//Safari Generic MacOSX
            //http://www.useragentstring.com/pages/useragentstring.php?typ=Browser
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",//Chrome 41.0.2228.0, Win 7
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246",//Edge 12.246 Win 10
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",//Firefox 40.1 Win7
    };
    //endregion
    //endregion

    //region constructors
    public JSoupMain(){
        Log.d(TAG, "constructor.");
        init();
    }
    public JSoupMain(String url){
        Log.d(TAG, "constructor..");
        init();
        this.url = url;
    }
    public JSoupMain(String url,Context context){
        Log.d(TAG, "constructor..");
        init();
        this.url = url;
        this.context = context;
    }
    public JSoupMain(String url, int userAgentNumber){
        Log.d(TAG, "constructor...");
        init();
        this.url = url;
        this.userAgentNumber = userAgentNumber;
    }

    private void init(){
        Log.d(TAG, "init");
        this.usareAgentList = new ArrayList<String>();
    }
    //endregion

    //region getters/setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getUsareAgentList() {
        return usareAgentList;
    }

    public void setUsareAgentList(List<String> usareAgentList) {
        this.usareAgentList = usareAgentList;
    }

    public int getUserAgentNumber() {
        return userAgentNumber;
    }

    public void setUserAgentNumber(int userAgentNumber) {
        this.userAgentNumber = userAgentNumber;
    }

    public void  addoUsareAgentList(String newUsareAgent){
        this.usareAgentList.add(newUsareAgent);
    }
    //endregion

    //region methods
    public void parse() throws ExecutionException, InterruptedException {
        DaoCP dao = new DaoCP(context);
        List<Source> l = dao.getSources();
        for(Iterator<Source> i = l.iterator(); i.hasNext();){
            Source s = i.next();
            Log.v(TAG, s.getName());
        }
        Connection connection = Jsoup.connect(url);
        String userAgent = uaArray[new Random().nextInt(uaArray.length)];
        connection.userAgent(userAgent);
        Log.v(TAG, "user agent: " + userAgent);
        Document doc = new GetDoc().execute(new String[]{url, userAgent}).get();
        //Log.v(TAG,doc.html());
        Elements uls = doc.select(".EntityList--Regular > ul");
        List<String> aList = new ArrayList<String>();
        for (Element el : uls){
            Log.v(TAG, el.outerHtml());
            Elements lis = el.getElementsByTag("li");
            for(Element li : lis){
                if(!li.hasClass("EntityList--VauVau") && !li.hasClass("EntityList--banner")) {

                    String a = li.getElementsByTag("a").attr("href").toString();
                    Log.w(TAG, a);
                }
            }
        }



    }

    //public Document getDocument (String url){}
    //endregion

    //region AsyncTask class
    private class GetDoc extends AsyncTask<String, Void, Document>{

        @Override
        protected Document doInBackground(String... params) {
            Connection connection = Jsoup.connect(params[0]);
            connection.userAgent(params[1]);
            Document doc = null;
            try {
                doc = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return doc;
        }

    }
    //endregion
}
