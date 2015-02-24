package org.motechproject.mds.domain;

import java.util.List;

/**
 * The <code>FieldInfo</code> class contains base information about the given entity field like its
 * name or type.
 *
 * @see org.motechproject.mds.service.JarGeneratorService
 */
public class FieldInfo {
    private String name;
    private String displayName;
    private TypeInfo typeInfo = new TypeInfo();
    private boolean required;
    private boolean restExposed;
    private boolean autoGenerated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isRestExposed() {
        return restExposed;
    }

    public void setRestExposed(boolean restExposed) {
        this.restExposed = restExposed;
    }

    public boolean isAutoGenerated() {
        return autoGenerated;
    }

    public void setAutoGenerated(boolean autoGenerated) {
        this.autoGenerated = autoGenerated;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    public String getTaskType() {
        return typeInfo.getTaskType();
    }

    public String getType() {
        return typeInfo.getType();
    }

    public class TypeInfo {
        private boolean isCombobox;
        private String type;
        private String taskType;
        private List<String> items;
        private boolean allowsMultipleSelection;
        private boolean allowUserSupplied;

        public boolean isAllowsMultipleSelection() {
            return allowsMultipleSelection;
        }

        public void setAllowsMultipleSelection(boolean allowsMultipleSelection) {
            this.allowsMultipleSelection = allowsMultipleSelection;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public boolean isCombobox() {
            return isCombobox;
        }

        public void setCombobox(boolean isCombobox) {
            this.isCombobox = isCombobox;
        }

        public boolean isAllowUserSupplied() {
            return allowUserSupplied;
        }

        public void setAllowUserSupplied(boolean allowUserSupplied) {
            this.allowUserSupplied = allowUserSupplied;
        }
    }
}
