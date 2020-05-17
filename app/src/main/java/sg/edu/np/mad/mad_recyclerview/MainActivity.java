package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button add;
    EditText newitemtext;
    RecyclerView items;
    List<String> listOfTodo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setrecyclerviewitems(listOfTodo);
        add = findViewById(R.id.additem);
        newitemtext = findViewById(R.id.additemedittext);
        items = findViewById(R.id.recyclerViewItems);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newitem = newitemtext.getText().toString();
                if(newitem.isEmpty()) {
                    Log.v(TAG,"empty input");
                }
                else{
                    listOfTodo.add(newitem);
                    setrecyclerviewitems(listOfTodo);
                    showNewEntry(items, (ArrayList) listOfTodo);
                    newitemtext.setText("");
                }
            }
        });
    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
    private void addNewEntry(String entryname){
        listOfTodo.add(entryname);
    }
    private void setrecyclerviewitems(List<String> mItemList)
    {
        RecyclerView items = (RecyclerView) findViewById(R.id.recyclerViewItems);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        items.setLayoutManager(llm);
        AdapterToDoList itemadapter  = new AdapterToDoList(mItemList);
        items.setAdapter(itemadapter);
    }
}
