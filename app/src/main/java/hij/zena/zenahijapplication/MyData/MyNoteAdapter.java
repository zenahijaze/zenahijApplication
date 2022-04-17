package hij.zena.zenahijapplication.MyData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import hij.zena.zenahijapplication.R;


public class MyNoteAdapter extends ArrayAdapter<MyNote>
{

    public MyNoteAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    /**
     * ملائمة المعطى طريقة عرضه
     * تقوم باخذ المعطى من قاعدة البيانات وبناءؤ واجهة وعرض هذه البيانات على الواجهة
     * وتثوم بارجاع الواجهة لكل معطى
     * @param position  رقم المعطى
     * @param convertView
     * @param parent
     * @return  تعيد واجهة عرض لمطى واحد حسب رقمه
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //View v=View.inflate(getContext(), R.layout.task_item_layout,parent);
        //بناء واجهة لمعطى واحد
        View v= LayoutInflater.from(getContext()).inflate(R.layout.msg_item_layout,parent,false);
        // استخراج المعطر حسب رقمه
        MyNote item = getItem(position);

        //تجهيز مؤشر لكل كائن على الواجهة
        TextView title=v.findViewById(R.id.itmTitle);
        TextView PackageName=v.findViewById(R.id.itmPackageName);
        TextView Text=v.findViewById(R.id.itmText);
        ImageButton BtnImpor=v.findViewById(R.id.itmBtnImpor);
        ImageButton BtnDel =v.findViewById(R.id.itmBtnDel);



        //وضع قيم المعطى المستخرج على كائنات الواجهة
        title.setText(item.getTitle());
        PackageName.setText(item.getPkgname());




        return v;
    }
}
