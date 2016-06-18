package com.androidActivities;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;

import com.controllers.Application;
import com.controllers.ItemController;

public class CreateItemActivity extends Fragment implements OnClickListener {

	View rootView;
	EditText itemnameEditText;
	EditText descriptionEditText;
	EditText usermailEditText;
	Spinner itemTypeSpinner;
	Button request;
	String currentEmail = "";

	String checkedCategories = "";
	ArrayList<CheckBox> checks = new ArrayList<CheckBox>();
	ArrayList<String> myCategories = new ArrayList<String>();

	public CreateItemActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_create_item, container,
				false);

		itemnameEditText = (EditText) rootView.findViewById(R.id.name);
		descriptionEditText = (EditText) rootView
				.findViewById(R.id.description);
		itemTypeSpinner = (Spinner) rootView.findViewById(R.id.ItemTypeSpinner);
		request = (Button) rootView.findViewById(R.id.request);

		myCategories = Application.getCategories();

		LinearLayout my_layout = (LinearLayout) rootView.findViewById(R.id.selectCategoryLayout_item);
		
		if (myCategories != null)
		{
			// loop of generation of check boxes
			for (int i = 0; i < myCategories.size(); i++) {
				TableRow row = new TableRow(getActivity());
				row.setId(i);
				row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				CheckBox checkBox = new CheckBox(getActivity());
				checkBox.setTag(myCategories);
				checkBox.setId(i);
				checkBox.setText(myCategories.get(i));
				checks.add(checkBox);
				row.addView(checkBox);
				my_layout.addView(row);

			}
		}
		

		currentEmail = Application.getCurrentUser().get_email();

		request.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (itemnameEditText.getText().toString().trim().equals("")) {
			itemnameEditText.setError("Item name is required!");

		} else {
			
			for (int i = 0; i < checks.size(); i++) 
			{
				if (checks.get(i).isChecked()) {
					checkedCategories += checks.get(i).getText();
					if (i != checks.size() - 1)
						checkedCategories += ";";	
				}
			}
			
			String selectedType = String.valueOf(itemTypeSpinner
					.getSelectedItem());
			ItemController itemController = new ItemController();
			itemController.createItem(itemnameEditText.getText().toString(),
					descriptionEditText.getText().toString(), currentEmail,
					Application.getCurrentUser().getDistrict(), "",
					checkedCategories, selectedType);

		}

	}

}
