package controller.group;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Group;
import model.Teacher;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import controller.managers.GroupManager;

@SuppressWarnings("serial")
public class DeleteGroupController extends ViewGroupController {

	public DeleteGroupController(GroupManager groupManager) {
		super(groupManager);		
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteGroup();				
			}
		});
	}

	protected void deleteGroup() {
		Group currentGroup = (Group) comboGroup.getSelectedItem();
		int confirmed = JOptionPane.showConfirmDialog(this, "¿Realente desea borrar este grupo?");
		if(confirmed == JOptionPane.OK_OPTION)
		{
			try {
				groupManager.deleteGroup(currentGroup);
				changeTeacher((Teacher) cboTeacher.getSelectedItem());
				JOptionPane.showMessageDialog(this, "Grupo borrado");
			} catch (NotFoundException | DeleteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} 		
		}
	}

	@Override
	protected String getButtonLabel() {	
		return "Borrar";
	}
}
