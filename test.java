class testpsp
{
public static void main(String gg[])
{
Tree<String> tree=new Tree<String>("root");
try
{
tree.addChild("child1","root");
tree.addChild("child2","root");
tree.addChild("child3","root");
tree.addChild("child4","root");
tree.addChild("child5","root");
tree.addChild("child51","child5");

Tree<String> temp=tree.get("child5");
System.out.println(temp.getData());
System.out.println(temp.getChildren());

//System.out.println(tree.delete("root"));

System.out.println(tree.delete("child5"));
temp=tree.get("child5");
System.out.println(temp);
temp=tree.get("child51");
System.out.println(temp);

MyTreeIterator<String> iterator=tree.getIterator();
String data;
while(iterator.hasNext())
{
data=iterator.next();
System.out.println(data);
}

}
catch(Exception e)
{
System.out.println(e);
}
}
}