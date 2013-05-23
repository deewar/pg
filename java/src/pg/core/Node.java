package pg.core;


import java.util.HashSet;

public class Node {
    private int id;
    private int priority;
    private int owner;
    private String Name;
    private HashSet<Integer> successorsIds = new HashSet<Integer>();
    private HashSet<Node> successors = new HashSet<Node>();
    private HashSet<Node> predecessors = new HashSet<Node>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public HashSet<Node> getSuccessors() {
        return successors;
    }

    public void setSuccessors(HashSet<Node> successors) {
        this.successors = successors;
    }

    public HashSet<Node> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(HashSet<Node> predecessors) {
        this.predecessors = predecessors;
    }

    public HashSet<Integer> getSuccessorsIds() {
        return successorsIds;
    }

    public void setSuccessorsIds(HashSet<Integer> successorsIds) {
        this.successorsIds = successorsIds;
    }

    public void deleteNode(){
        for (Node n : getPredecessors()){
            n.removeFromSuccessors(this);
        }
        for (Node n : getSuccessors()){
            n.removeFromPredecessors(this);
        }
    }

    public void removeSuccessor(Node n){
        this.removeFromSuccessors(n);
        n.removeFromPredecessors(this);
    }

    public void removeFromSuccessors(Node node) {
        if (!getSuccessors().remove(node))
            throw new RuntimeException("Tried to remove a node which never existed");
    }

    public void removeFromPredecessors(Node node) {
       if (!getPredecessors().remove(node))
            throw new RuntimeException("Tried to remove a node which never existed");
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ");
        sb.append(getId());
        sb.append(" Priority: ");
        sb.append(getPriority());
        sb.append(" Owner: ");
        sb.append(getOwner());
        sb.append(" Succ: ");
        for(Node n : getSuccessors()){
            sb.append(n.getId());
            sb.append(",");
        }
        if (getSuccessors().size() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append(" Pred: ");
        for(Node n : getPredecessors()){
            sb.append(n.getId());
            sb.append(",");
        }
        if (getPredecessors().size() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append(" Name: ");
        sb.append(getName());
        return  sb.toString();
    }

}
