package net.mgsx.pd.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import net.mgsx.pd.Pd;
import net.mgsx.pd.patch.PdPatch;

public class AtmosphereDemo implements Demo
{
	private PdPatch patch;
	
	@Override
	public Actor create(Skin skin) 
	{
		Table root = new Table(skin);
		
		addControl(root, "Rain", "rain");
		addControl(root, "Wind", "wind");
		addControl(root, "Storm", "storm");
		addControl(root, "Fire", "fire");
		
		patch = Pd.audio.open(Gdx.files.internal("pd/atmosphere.pd"));
		
		return root;
	}
	
	private void addControl(Table table, String label, final String receiver)
	{
		final Slider control = new Slider(0, 1, .01f, false, table.getSkin());
		control.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Pd.audio.sendFloat(receiver, control.getValue());
			}
		});
		
		table.add(label);
		table.add(control).row();
	}

	@Override
	public void dispose() {
		patch.dispose();
	}
	
	@Override
	public String toString() {
		return "Atmosphere";
	}

}
