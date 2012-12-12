package adapters;

import java.util.List;

import beans.Lojas;
import br.com.saara.R;
  
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LojaAdapter extends BaseAdapter{
    private List<Lojas> listLojas;
    Context context;
 
    //Classe utilizada para instanciar os objetos do XML
    private LayoutInflater inflater;
    private int idLayout;
     
    public LojaAdapter(Context context, List<Lojas> listLojas, int idLayout ) {
        this.listLojas = listLojas;
        this.context = context;
        this.idLayout = idLayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public void addItem(final Lojas item) {
        this.listLojas.add(item);
        //Atualizar a lista caso seja adicionado algum item
        notifyDataSetChanged();
    }    
        
	public int getCount() {
        return listLojas.size();
    }
   
	public Object getItem(int position) {
        return listLojas.get(position);
    }
 
	public long getItemId(int position) {
        return position;
    }

	public View getView(int position, View convertView, ViewGroup viewGroup) {
         
        Lojas estadoVO = listLojas.get(position);
 
        ViewHolder holder;
   
        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);
    
            holder = new ViewHolder();
            holder.icone = (ImageView)convertView.findViewById(R.id.imgIconLoja);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
           
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.title.setText(estadoVO.getNome());
       
        return convertView;
 
    }
  
    static class ViewHolder {
        public ImageView icone;
        public TextView title;
    }
}