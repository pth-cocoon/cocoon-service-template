package icu.cocoon.util;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xce
 * @date 2020/4/1  14:34
 */
public class TreeUtil {

  public static TreeNode getInitTree(List<? extends BaseTree> nodes) {
    List<TreeNode> list = transformToTreeNode(nodes);
    Map<Long, List<TreeNode>> map = list.stream().collect(Collectors.groupingBy(TreeNode::getParentId));
    TreeNode root = new TreeNode(0L, 0L, "全部", null);
    generateLeaf(root, map);
    return root;
  }

  private static void generateLeaf(TreeNode node, Map<Long, List<TreeNode>> map) {
    if (map.get(node.getId()) != null) {
      node.setChildren(map.get(node.getId()));
      node.getChildren().forEach(treeNode -> generateLeaf(treeNode, map));
    }
  }


  private static TreeNode transformToTreeNode(BaseTree tree) {
    return new TreeNode(tree.getId(), tree.getParentId(), tree.getLabel(), null);
  }

  private static List<TreeNode> transformToTreeNode(List<? extends BaseTree> treeList) {
    return treeList.stream().map(TreeUtil::transformToTreeNode).collect(Collectors.toList());
  }


}
