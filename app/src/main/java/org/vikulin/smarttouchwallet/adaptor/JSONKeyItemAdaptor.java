package org.vikulin.smarttouchwallet.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.vikulin.smarttouchwallet.R;
import org.vikulin.smarttouchwallet.icon.Blockies;
import java.util.List;

import static org.web3j.utils.Numeric.prependHexPrefix;

/**
 * Created by vadym on 01.12.16.
 */

public class JSONKeyItemAdaptor<L extends List<?>> extends BaseExpandableListAdapter {

    private Context context;
    protected List<List<JSONObject>> data = null;

    private static int[] groupIcons = new int[] {
        R.drawable.eth, R.drawable.btc, R.drawable.dash, R.drawable.zcash, R.drawable.ec, R.drawable.litecoin
    } ;

    public JSONKeyItemAdaptor(Context context, List<List<JSONObject>> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        View row = convertView;
        KeyHolder holder = null;
        JSONObject wallet = data.get(groupPosition).get(childPosition);
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.list_wallet_key, parent, false);
            holder = new KeyHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.idIcon);
            holder.address = (TextView)row.findViewById(R.id.address);
            row.setTag(holder);
        } else {
            holder = (KeyHolder)row.getTag();
        }
        String address = null;
        try {
            address = prependHexPrefix(wallet.getString("address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.address.setText(address);
        holder.imgIcon.setImageBitmap(Blockies.createIcon(8, address, 12));
        return row;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupIndex, boolean b, View view, ViewGroup viewGroup) {
        View row = view;
        CurrencyHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.currency_list_item, viewGroup, false);
            holder = new CurrencyHolder();
            holder.currencyIcon = (ImageView)row.findViewById(R.id.currencyIcon);
            holder.balance = (TextView)row.findViewById(R.id.balance);
            row.setTag(holder);
        } else {
            holder = (CurrencyHolder)row.getTag();
        }
        holder.balance.setText("0");
        holder.currencyIcon.setImageDrawable(context.getDrawable(groupIcons[groupIndex]));
        return row;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    static class KeyHolder
    {
        ImageView imgIcon;
        TextView address;
    }

    static class CurrencyHolder
    {
        ImageView currencyIcon;
        TextView balance;
    }
}