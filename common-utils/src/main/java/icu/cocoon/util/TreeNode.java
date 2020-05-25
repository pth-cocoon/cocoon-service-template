package icu.cocoon.util;


import java.util.List;

/**
 * @author xce
 * @date 2020/4/1  14:31
 */
public class TreeNode {

  private Long id;
  private Long parentId;
  private String label;
  private List<TreeNode> children;

  public TreeNode(Long id, Long parentId, String label, List<TreeNode> children) {
    this.id = id;
    this.parentId = parentId;
    this.label = label;
    this.children = children;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<TreeNode> getChildren() {
    return children;
  }

  public void setChildren(List<TreeNode> children) {
    this.children = children;
  }
}
