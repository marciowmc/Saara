package adapters;

import java.util.List;

import beans.Categorias;
import br.com.saara.R;
  
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CategoriaAdapter extends BaseAdapter{
    private List<Categorias> listCategorias;
    Context context;
 
    //Classe utilizada para instanciar os objetos do XML
    private LayoutInflater inflater;
    private int idLayout;
     
    public CategoriaAdapter(Context context, List<Categorias> listCategorias, int idLayout ) {
        this.listCategorias = listCategorias;
        this.context = context;
        this.idLayout = idLayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public void addItem(final Categorias item) {
        this.listCategorias.add(item);
        //Atualizar a lista caso seja adicionado algum item
        notifyDataSetChanged();
    }    
        
	public int getCount() {
        return listCategorias.size();
    }
   
	public Object getItem(int position) {
        return listCategorias.get(position);
    }
 
	public long getItemId(int position) {
        return position;
    }

	public View getView(int position, View convertView, ViewGroup viewGroup) {
         
        Categorias estadoVO = listCategorias.get(position);
 
        //O ViewHolder ir‡ guardar a inst‰ncias dos objetos do estado_row
        ViewHolder holder;
   
        //Quando o objeto convertView n‹o for nulo n—s n‹o precisaremos inflar
        //os objetos do XML, ele ser‡ nulo quando for a primeira vez que for carregado
        if (convertView == null) {
            convertView = inflater.inflate(idLayout, null);
    
            //Cria o Viewholder e guarda a inst‰ncia dos objetos
            holder = new ViewHolder();
            holder.icone = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
           
            convertView.setTag(holder);
        } else {
            //pega o ViewHolder para ter um acesso r‡pido aos objetos do XML
            //ele sempre passar‡ por aqui quando,por exemplo, for efetuado uma rolagem na tela 
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.title.setText(estadoVO.getText());
        holder.icone.setImageResource(estadoVO.getIcon());
       
        return convertView;
 
    }
  
    //Criada esta classe est‡tica para guardar a referncia dos objetos abaixo
    static class ViewHolder {
        public ImageView icone;
        public TextView title;
    }
}