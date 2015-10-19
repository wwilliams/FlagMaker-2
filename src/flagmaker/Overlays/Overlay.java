package flagmaker.Overlays;

import flagmaker.Overlays.Attributes.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Overlay
{
	public final String Name;
	public boolean IsEnabled;
	public Color Color;
	public Attribute[] Attributes;
	protected int MaximumX;
	protected int MaximumY;
	
	protected abstract Shape[] Thumbnail();
	
	public abstract void Draw(Pane canvas);
	public abstract void SetValues(Object[] values);
	public abstract String ExportSvg(int width, int height);
	
	protected Overlay(String name, Attribute[] attributes, int maximumX, int maximumY)
	{
		Name = name;
		IsEnabled = true;
		Color = Color.BLACK;
		Attributes = attributes;
		SetMaximum(maximumX, maximumY);
	}
	
	protected Overlay(String name, Color color, Attribute[] attributes, int maximumX, int maximumY)
	{
		Name = name;
		IsEnabled = true;
		Color = color;
		Attributes = attributes;
		SetMaximum(maximumX, maximumY);
	}
	
	public void SetColor(Color color)
	{
		Color = color;
	}
	
	public final void SetMaximum(int maximumX, int maximumY)
	{
		MaximumX = maximumX;
		MaximumY = maximumY;
	}
	
	public Pane PaneThumbnail()
	{
		Pane p = new Pane();
		p.setMinHeight(30);
		p.setMinWidth(30);
	
		for (Shape thumb : Thumbnail())
		{
			thumb.setStroke(Color.BLACK);
			if (thumb.getStrokeWidth() == 1.0) thumb.setStrokeWidth(0);
			if (thumb.fillProperty().get() == null) thumb.setFill(Color.BLACK);
			p.getChildren().add(thumb);
		}
		
		return p;
	}
	
	public <T> void SetAttribute(String name, T value)
	{
		for (Attribute a : Attributes)
		{
			if (a.Name.equals(name))
			{
				a.SetValue(value);
				return;
			}
		}
		
		// Attribute not found
	}
	
	public Attribute GetAttribute(String name)
	{
		for (Attribute a : Attributes)
		{
			if (a.Name.equals(name))
			{
				return a;
			}
		}
		
		// Attribute not found
		return null;
	}
	
	public double GetDoubleAttribute(String name)
	{
		for (Attribute a : Attributes)
		{
			if (a.Name.equals(name) && a instanceof DoubleAttribute)
			{
				return ((DoubleAttribute)a).Value;
			}
		}
		
		// Attribute not found
		return 0;
	}
	
	public int GetIntegerAttribute(String name)
	{
		for (Attribute a : Attributes)
		{
			if (a.Name.equals(name) && a instanceof IntegerAttribute)
			{
				return ((IntegerAttribute)a).Value;
			}
		}
		
		// Attribute not found
		return 0;
	}
	
	public boolean GetBooleanAttribute(String name)
	{
		for (Attribute a : Attributes)
		{
			if (a.Name.equals(name) && a instanceof BooleanAttribute)
			{
				return ((BooleanAttribute)a).Value;
			}
		}
		
		// Attribute not found
		return false;
	}
}
