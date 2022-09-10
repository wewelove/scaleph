import {Dict, ModalFormProps} from '@/app.d';
import {ClusterCredential} from '@/services/resource/typings';
import {Form, Input, message, Modal, Select} from 'antd';
import {useIntl} from 'umi';
import {useEffect, useState} from "react";
import {listDictDataByType} from "@/services/admin/dictData.service";
import {DICT_TYPE} from "@/constant";
import {add, update} from '@/services/resource/clusterCredential.service';

const ClusterCredentialForm: React.FC<ModalFormProps<ClusterCredential>> = ({
  data,
  visible,
  onVisibleChange,
  onCancel,
}) => {
  const intl = useIntl();
  const [form] = Form.useForm();
  const [clusterTypeList, setClusterTypeList] = useState<Dict[]>([]);
  useEffect(() => {
    listDictDataByType(DICT_TYPE.resourceClusterType).then((d) => {
      setClusterTypeList(d);
    });
  }, []);


  return (
    <Modal
      visible={visible}
      title={
        data.id
          ? intl.formatMessage({ id: 'app.common.operate.edit.label' }) +
            intl.formatMessage({ id: 'pages.resource.clusterCredential' })
          : intl.formatMessage({ id: 'app.common.operate.new.label' }) +
            intl.formatMessage({ id: 'pages.resource.clusterCredential' })
      }
      width={580}
      destroyOnClose={true}
      onCancel={onCancel}
      onOk={() => {
        form.validateFields().then((values) => {
          const param: ClusterCredential = {
            id: values.id,
            configType: { value: values.configType },
            name: values.name,
            remark: values.remark
          };
          data.id
            ? update(param).then((d) => {
              if (d.success) {
                message.success(intl.formatMessage({ id: 'app.common.operate.edit.success' }));
                onVisibleChange(false);
              }
            })
            : add(param).then((d) => {
              if (d.success) {
                message.success(intl.formatMessage({ id: 'app.common.operate.new.success' }));
                onVisibleChange(false);
              }
            });
        });
      }}
    >
      <Form
        form={form} layout="horizontal" labelCol={{ span: 6 }} wrapperCol={{ span: 16 }}
        initialValues={{
          id: data.id,
          configType: data.configType?.value,
          name: data.name,
          remark: data.remark,
        }}
      >
        <Form.Item name="id" hidden>
          <Input></Input>
        </Form.Item>
        <Form.Item
          name="configType"
          label={intl.formatMessage({ id: 'pages.resource.clusterCredential.configType' })}
          rules={[{ required: true }, { max: 128 }]}
        >
          <Select
            disabled={data.id ? true : false}
            showSearch={true}
            allowClear={true}
            optionFilterProp="label"
            filterOption={(input, option) =>
              (option!.children as unknown as string)
                .toLowerCase()
                .includes(input.toLowerCase())
            }
          >
            {clusterTypeList.map((item) => {
              return (
                <Select.Option key={item.value} value={item.value}>
                  {item.label}
                </Select.Option>
              );
            })}
          </Select>
        </Form.Item>
        <Form.Item
          name="name"
          label={intl.formatMessage({ id: 'pages.resource.clusterCredential.name' })}
          rules={[
            { required: true },
            { max: 30 },
            {
              pattern: /^[\w\s_]+$/,
              message: intl.formatMessage({ id: 'app.common.validate.characterWord3' }),
            },
          ]}
        >
          <Input></Input>
        </Form.Item>
        <Form.Item
          name="remark"
          label={intl.formatMessage({ id: 'pages.resource.remark' })}
          rules={[{ max: 200 }]}
        >
          <Input></Input>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default ClusterCredentialForm;