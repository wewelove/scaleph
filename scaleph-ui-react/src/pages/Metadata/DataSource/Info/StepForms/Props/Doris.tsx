import {useIntl, useModel} from "@umijs/max";
import {Form} from "antd";
import {useEffect} from "react";
import {ProCard, ProFormSelect, ProFormText, ProFormTextArea} from "@ant-design/pro-components";
import {DsCategoryService} from "@/services/datasource/category.service";

const DorisForm: React.FC = () => {
  const intl = useIntl();
  const form = Form.useFormInstance()

  const {dsType} = useModel('dataSourceType', (model) => ({
    dsType: model.dsType
  }));

  useEffect(() => {
    form.setFieldValue("dsTypeId", dsType?.id)
  }, [dsType])

  return (
    <div>
      <ProCard
        headerBordered={true}
        style={{width: 1000}}>
        <ProFormSelect
          name="dsTypeId"
          label={intl.formatMessage({id: 'pages.metadata.dataSource.step.props.type'})}
          colProps={{span: 21, offset: 1}}
          disabled
          showSearch={false}
          request={() => {
            return DsCategoryService.listTypes({}).then((response) => {
              if (response.data) {
                return response.data.map((item) => {
                  return {label: item.type.label, value: item.id, item: item};
                });
              }
              return []
            })
          }}
        />
        <ProFormText
          name="version"
          label={intl.formatMessage({id: 'pages.metadata.dataSource.step.props.version'})}
          colProps={{span: 21, offset: 1}}
        />
        <ProFormText
          name="name"
          label={intl.formatMessage({id: 'pages.metadata.dataSource.step.props.name'})}
          colProps={{span: 21, offset: 1}}
          rules={[{required: true}]}
        />
        <ProFormTextArea
          name="remark"
          label={intl.formatMessage({id: 'app.common.data.remark'})}
          colProps={{span: 21, offset: 1}}
          fieldProps={{
            rows: 5
          }}
        />
        <ProFormText
          name="nodeUrls"
          label={intl.formatMessage({id: 'pages.metadata.dataSource.step.props.doris.nodeUrls'})}
          placeholder={intl.formatMessage({id: 'pages.metadata.dataSource.step.props.doris.nodeUrls.placeholder'})}
          colProps={{span: 21, offset: 1}}
          rules={[{required: true}]}
          initialValue={"localhost:8030"}
        />
        <ProFormText
          name="username"
          label={intl.formatMessage({id: 'pages.metadata.dataSource.step.props.doris.username'})}
          colProps={{span: 21, offset: 1}}
          rules={[{required: true}]}
        />
        <ProFormText
          name="password"
          label={intl.formatMessage({id: 'pages.metadata.dataSource.step.props.doris.password'})}
          colProps={{span: 21, offset: 1}}
          rules={[{required: true}]}
        />
      </ProCard>
    </div>
  );
}

export default DorisForm;

