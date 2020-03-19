package collection.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BTreeNode {
    private BTreeNode left;
    private BTreeNode right;
    private Integer data;

    public BTreeNode(Integer data) {
        this.data = data;
    }



}
