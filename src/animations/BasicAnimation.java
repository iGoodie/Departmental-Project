package animations;

public class BasicAnimation implements Cloneable{
	PairAnimation scl, tr;
	ValueAnimation rot;

	public BasicAnimation(boolean _scl, boolean _tr, boolean _rot){
		if(_scl){
			scl = new PairAnimation();
		}
		if(_tr){
			tr = new PairAnimation();
		}
		if(_rot){
			rot = new ValueAnimation();
		}
	}

	public BasicAnimation copy(){
		BasicAnimation a = new BasicAnimation(scl!=null, tr!=null, rot!=null);
		if(scl!=null){
			a.setScaleTimes(scl.getTimePts());
			a.setScaleXValues(scl.getValue1Pts());
			a.setScaleYValue(scl.getValue2Pts());
		}
		if(rot!=null){
			a.setRotationTimes(rot.getTimePts());
			a.setRotationValues(rot.getValuePts());
		}
		if(tr!=null){
			a.setTranslateTimes(tr.getTimePts());
			a.setTranslateXValues(tr.getValue1Pts());
			a.setTranslateYValues(tr.getValue2Pts());
		}
		return a;
	}

	/*scl related*/
	public void setScaleTimes(int...times){
		scl.setTimePts(times);
	}

	public void setScaleXValues(float...values){
		scl.setValue1Pts(values);
	}

	public void setScaleYValue(float...values){
		scl.setValue2Pts(values);
	}

	public float getScaleX(){
		return scl==null ? 1 : Float.isNaN(scl.getValue1()) ? 1 : scl.getValue1();
	}

	public float getScaleY(){
		return scl==null ? 1 : Float.isNaN(scl.getValue2()) ? 1 : scl.getValue2();
	}

	/*tr related*/
	public void setTranslateTimes(int...times){
		tr.setTimePts(times);
	}

	public void setTranslateXValues(float...values){
		tr.setValue1Pts(values);
	}

	public void setTranslateYValues(float...values){
		tr.setValue2Pts(values);
	}

	public float getTranslateX(){
		return tr==null ? 0 : Float.isNaN(tr.getValue1()) ? 0 : tr.getValue1();
	}

	public float getTranslateY(){
		return tr==null ? 0 : Float.isNaN(tr.getValue2()) ? 0 : tr.getValue2();
	}

	/*rot related*/
	public void setRotationTimes(int...times){
		rot.setTimePts(times);
	}

	public void setRotationValues(float...values){
		rot.setValuePts(values);
	}

	public float getRotation(){
		return rot==null ? 0 : rot.getValue();
	}

	/*update*/
	public void update(int dt){
		if(scl!=null){
			scl.update(dt);
		}
		if(rot!=null){
			rot.update(dt);
		}
		if(tr!=null){
			tr.update(dt);
		}
	}

	public void reset(){
		if(scl!=null){
			scl.reset();
		}
		if(rot!=null){
			rot.reset();
		}
		if(tr!=null){
			tr.reset();
		}
	}
}
