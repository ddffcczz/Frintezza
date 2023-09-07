package com.example.frintezza;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketClient extends AppCompatActivity{
    ViewGroup loginContainer= null;
    private static final String WEBSOCKET_URL = "http://158.160.17.221:65080";
    private WebSocket webSocket;
    private Context context;
    public WebSocketClient (AppCompatActivity context, String token, String set_act) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(WEBSOCKET_URL)
                .build();
        this.context = context;
        JsonProto connect = new JsonProto(set_act,token,null);
        Gson gson = new Gson();
        String jsonString = gson.toJson(connect);
        WebSocketListener socketListener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                webSocket.send(jsonString);
            }

            @Override
            public void onMessage (WebSocket webSocket, String text) {
                Gson gson2 = new Gson();
                JsonProto result = gson2.fromJson(text, JsonProto.class);
                switch (result.getAction()){
                    case "connect":
                        if(result.getStatus().equals("true")){
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run() {
                                    Intent intent = new Intent(context, ListActivity.class);
                                    context.startActivity(intent);
                                }
                            });

                            //go-to next-page
                        }else{
                            loginContainer = (ViewGroup) context.findViewById(R.id.inputContainer);
                            TextView textV1 = (TextView) context.findViewById(R.id.textView1);
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run() {
                                    loginContainer.setVisibility(View.VISIBLE);
                                    textV1.setText("Неправильный логин или пароль!");
                                }
                            });
                        }
                        stop();
                        break;
                    case "getdata":
                        Gson gson3 = new Gson();
                        JsonProtoData result2 = gson2.fromJson(text, JsonProtoData.class);
                        System.out.println(result2.getData());
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                RecyclerView recyclerView = context.findViewById(R.id.rvWords);
                                recyclerView.setAdapter(new RouterAdapter(result2.getData()));
                            }
                        });
                        break;
                }

                System.out.println("---------------------------------==text3 "+text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
            }
            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
            }
            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println("---------------------------------==Ошибка! "+t);
            }
        };
        webSocket = client.newWebSocket(request, socketListener);
    }

    public void stop() {
        if (webSocket != null) {
            webSocket.cancel();
        }
    }
}
