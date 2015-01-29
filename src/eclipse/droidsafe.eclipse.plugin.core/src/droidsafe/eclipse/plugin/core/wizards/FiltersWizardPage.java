package droidsafe.eclipse.plugin.core.wizards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import droidsafe.eclipse.plugin.core.filters.Filter;
import droidsafe.eclipse.plugin.core.view.DroidsafeImages;

/**
 * A wizard page for editing the filters for the current indicator display.
 * 
 * @author gilham
 *
 */
public class FiltersWizardPage extends WizardPage {

    /** The array of fields which can be used for filtering the current indicator display. */
    private String[] filterFields;
    
    /** The resulting new filters. */
    private List<Filter> newFilters;

    /** The table of filters. */
    private Table table;

    /** The table viewer for the table of filters. */
    private TableViewer tableViewer;

    /** A container for the delete button and the edit button. */
    private Composite buttonsContainer;

    /** An Button widget for deleting the selected filter */
    private Button deleteButton;

    /** An Button widget for editing the selected filter */
    private Button editButton;

    /**
     * Constructs a FiltersWizardPage.
     * 
     * @param filters - the filters being edited
     * @param filterFields - The fields that can be used for filtering the indicator display 
     */
    public FiltersWizardPage(List<Filter> filters, String[] filterFields) {
        super("wizardPage");
        setTitle("View or Set Filters");
        setDescription("View or set the list of filters for the indicator outline view.");
        this.newFilters = new ArrayList<Filter>();
        for (Filter filter: filters) {
            newFilters.add(new Filter(filter));
        }
        this.filterFields = filterFields;
    }

    /**
     * Returns the new list of filters.
     */
    public List<Filter> getNewFilters() {
        return newFilters;
    }
    
    /**
     * Creates contents of the wizard.
     * 
     * @param parent - the parent composite
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        setControl(container);
        container.setLayout(new GridLayout(2, false));
        
        tableViewer = new TableViewer(container, SWT.MULTI | SWT.H_SCROLL
            | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

        table = tableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(false);
        
        createColumns(container);

        tableViewer.setContentProvider(ArrayContentProvider.getInstance());
        tableViewer.setInput(newFilters);
        
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        tableViewer.getControl().setLayoutData(gridData);
        
        buttonsContainer = new Composite(container, SWT.NULL);
        gridData = new GridData(SWT.LEFT, SWT.FILL, false, false);
        buttonsContainer.setLayoutData(gridData);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.pack = false;
        buttonsContainer.setLayout(rowLayout);

        editButton = new Button(buttonsContainer, SWT.NONE);
        editButton.setText("Edit");
        editButton.setEnabled(false);
        editButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ISelection selection = tableViewer.getSelection();
                if (selection != null && selection instanceof IStructuredSelection) {
                    IStructuredSelection sel = (IStructuredSelection) selection;
                    for (Iterator<Filter> iterator = sel.iterator(); iterator.hasNext();) {
                      Filter filter = iterator.next();
                      editFilter(filter);
                    }
                    tableViewer.refresh();
                  }
            }
        });

        deleteButton = new Button(buttonsContainer, SWT.NONE);
        deleteButton.setText("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ISelection selection = tableViewer.getSelection();
                if (selection != null && selection instanceof IStructuredSelection) {
                    IStructuredSelection sel = (IStructuredSelection) selection;
                    for (Iterator<Filter> iterator = sel.iterator(); iterator.hasNext();) {
                      Filter filter = iterator.next();
                      newFilters.remove(filter);
                    }
                    tableViewer.refresh();
                  }
            }
        });

        tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection)event.getSelection();
                boolean selected = (selection != null && !selection.isEmpty());
                editButton.setEnabled(selected);
                deleteButton.setEnabled(selected);
            }
        });
    }
    
    /**
     * Presents an EditFiterWizard for the user to edit the given filter.
     * 
     * @param filter - a filter
     */
    private void editFilter(Filter filter) {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        EditFilterWizard wizard = new EditFilterWizard();
        wizard.init(newFilters, filter, filterFields);
        // Create the wizard dialog
        WizardDialog dialog = new WizardDialog(window.getShell(),wizard);
        // Open the wizard dialog
        dialog.open();

    }

    /**
     * Creates table columns for the names, the specs, and enabled statuses of the filters.
     * 
     * @param parent - the parent composite
     */
    private void createColumns(Composite parent) {
        TableViewerColumn nameCol = createTableViewerColumn("Filter Name", 150, 0);
        nameCol.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Filter filter = (Filter) element;
                String name = filter.name;
                return (name == null) ? "" : name;
            }
        });

        TableViewerColumn filterCol = createTableViewerColumn("Filter Spec", 500, 0);
        filterCol.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                Filter filter = (Filter) element;
                return filter.type + " " + filter.pred;
            }
        });

        TableViewerColumn enabledCol = createTableViewerColumn("Enabled", 50, 1);
        enabledCol.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                return null;
            }

            @Override
            public Image getImage(Object element) {
                if (((Filter) element).isEnabled()) {
                    return DroidsafeImages.CHECKED;
                }
                return DroidsafeImages.UNCHECKED;
            }
        });
        enabledCol.setEditingSupport(new EnabledEditingSupport());
    }

    /**
     * Creates one table viewer column with the given title, width, and column number.
     * Returns the resulting column.
     */
    private TableViewerColumn createTableViewerColumn(String title, int width,
                                                      final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer,
            SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(width);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    /**
     * An editing support for editing the enabled status of a filter.
     * 
     * @author gilham
     *
     */
    public class EnabledEditingSupport extends EditingSupport {

        /**
         * Constructs an EnabledEditingSupport.
         */
        public EnabledEditingSupport() {
            super(tableViewer);
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }
        
        @Override
        protected CellEditor getCellEditor(Object element) {
            return new CheckboxCellEditor(null, SWT.CHECK | SWT.READ_ONLY);
        }

        @Override
        protected Object getValue(Object element) {
            Filter filter = (Filter) element;
            return filter.isEnabled();

        }

        @Override
        protected void setValue(Object element, Object value) {
            Filter filter = (Filter) element;
            filter.setEnabled((Boolean) value);
            tableViewer.update(element, null);
        }
    }
    
}