import Model.ResponseModel;
import Network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class ResponseGUI {
    public  JPanel UIresponse;
    private JTextField Message;
    private JTextField Status;
    private JTextField Coment;
    private JButton submiteButton;
    private JButton exitButton;
    private JButton Minimize;
    public JButton getMinimize() {return Minimize;}


    public ResponseGUI() {
        submiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message.setText("");
                Status.setText("");
                Coment.setText("");
                try{
                    ConnectURI connection = new ConnectURI();
                    URL myAddress = connection.buildURL("http://harber.mimoapps.xyz/api/getaccess.php");
                    String response = connection.getResponseFromHttpUrl(myAddress);

                    JSONArray responseJSON = new JSONArray(response);
                    ArrayList<ResponseModel> responseModel = new ArrayList<>();
                    for (int i = 0; i < responseJSON.length(); i++) {
                        ResponseModel resModel = new ResponseModel();
                        JSONObject myJSONObject = responseJSON.getJSONObject(i);
                        resModel.setMsg(myJSONObject.getString("message"));
                        resModel.setStatus(myJSONObject.getString("status"));
                        resModel.setComent(myJSONObject.getString("comment"));
                        responseModel.add(resModel);
                    }
                    for(ResponseModel respond : responseModel){
                        Message.setText(respond.getMsg());
                        Status.setText(respond.getStatus());
                        Coment.setText(respond.getComent());
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }

            }
        });

        Minimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
    }
}
