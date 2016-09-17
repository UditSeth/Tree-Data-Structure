import java.util.*;

public class Tree<T>
{
private T data;
private Tree<T> parent;
private HashMap<Integer,Tree<T>> children;

public Tree(T rootData)
{
this.data=rootData;
this.parent=null;
this.children=new HashMap<Integer,Tree<T>>();
}

public T getData()
{
return this.data;
}

public HashMap<Integer,T> getChildren()
{
HashMap<Integer,T> temp=new HashMap<Integer,T>();
for(Map.Entry<Integer,Tree<T>> entry : this.children.entrySet())
temp.put(entry.getKey(),entry.getValue().data);
return temp;
}

public Tree<T> addChild(T data,T parent) throws Exception
{
Tree<T> parentNode=this.get(parent);
if(parentNode==null) throw new Exception("Parent node does not exists.");
Tree<T> temp=new Tree<T>(data);
temp.parent=parentNode;
parentNode.children.put(data.hashCode(),temp);
return temp; 
}

public Tree<T> get(T data)
{
int hashCode=data.hashCode();
if(this.data.hashCode()==hashCode) return this;
Stack<Tree<T>> stack=new Stack<Tree<T>>();
stack.push(this);
Tree<T> temp,a;
while(true)
{
temp=stack.pop();
if(temp.hasChildren())
{
a=temp.children.get(hashCode);
if(a!=null) return a;
for(Map.Entry<Integer,Tree<T>> entry : temp.children.entrySet())
{
stack.push(entry.getValue());
}
}
else
{
if(stack.empty()) break;
}
}
return null;
}

public boolean delete(T data)
{
Tree<T> temp=this.get(data);
if(temp==null) return false;
temp=temp.getParent();
if(temp==null) return false;
temp.children.remove(data.hashCode());
return true;
}

public Tree<T> getParent()
{
return this.parent;
}

public boolean hasChildren()
{
return !(this.children.isEmpty());
}

public MyTreeIterator<T> getIterator()
{
return new TreeIterator<T>(this);
}

//inner class
class TreeIterator<T> implements MyTreeIterator<T>
{
private ArrayList<T> arrayList;
private int index;

public TreeIterator(Tree<T> tree)
{
this.arrayList=new ArrayList<T>();
this.index=-1;
Stack<Tree<T>> stack=new Stack<Tree<T>>();
stack.push(tree);
this.arrayList.add(tree.data);
Tree<T> temp;
while(true)
{
temp=stack.pop();
if(temp.hasChildren())
{
for(Map.Entry<Integer,Tree<T>> entry : temp.children.entrySet())
{
stack.push(entry.getValue());
this.arrayList.add(entry.getValue().data);
}
}
else
{
if(stack.empty()) break;
}
}
}

public boolean hasNext()
{
this.index++;
if(this.index<this.arrayList.size()) return true;
this.index=-1;
return false;
}

public T next()
{
if(this.index==-1) return null;
return this.arrayList.get(index);
}

}

}